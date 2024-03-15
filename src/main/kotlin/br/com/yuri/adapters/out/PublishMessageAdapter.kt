package br.com.yuri.adapters.out

import br.com.yuri.adapters.`in`.consumer.exception.ErrorToPublishKafkaMessageException
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.MessageDomain
import br.com.yuri.application.ports.out.PublishMessageOutputPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PublishMessageAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
) : PublishMessageOutputPort {

    override fun send(message: MessageDomain, context: Context) {
        val (topic, content) = message
        try {
            kafkaTemplate.send(topic, content)
        } catch (ex: Exception) {
            when (context) {
                Context.REST -> throw RuntimeException(ex.message)
                Context.KAKFA -> throw ErrorToPublishKafkaMessageException(ex.message ?: "Unknown error")
            }
        }
    }
}
