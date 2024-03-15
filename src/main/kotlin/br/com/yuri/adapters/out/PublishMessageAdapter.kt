package br.com.yuri.adapters.out

import br.com.yuri.adapters.`in`.consumer.exception.ErrorToPublishKafkaMessageException
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.MessageDomain
import br.com.yuri.application.ports.out.PublishMessageOutputPort
import br.com.yuri.config.GenericLogger
import org.slf4j.Logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PublishMessageAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
) : PublishMessageOutputPort {

    private val log: Logger = GenericLogger.loggerFor(PublishMessageAdapter::class.java)

    override fun send(message: MessageDomain, context: Context) {
        val (topic, content) = message
        try {
            log.info("Posting message to Kafka topic: $topic, message: $content, context: $context")
            kafkaTemplate.send(topic, content)
            log.info("Operation carried out successfully")
        } catch (ex: Exception) {
            val errorMessage = "Error! An error occurred while posting message to kafka: ${ex.message}"
            when (context) {
                Context.REST -> {
                    log.error(errorMessage)
                    throw RuntimeException(errorMessage)
                }

                Context.KAKFA -> {
                    log.error(errorMessage)
                    throw ErrorToPublishKafkaMessageException(errorMessage)
                }
            }
        }
    }
}
