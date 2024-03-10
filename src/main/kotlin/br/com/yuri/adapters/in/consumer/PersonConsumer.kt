package br.com.yuri.adapters.`in`.consumer

import br.com.yuri.adapters.`in`.consumer.message.PersonMessage
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PersonConsumer(
    private val objectMapper: ObjectMapper
) {
    private val log = LoggerFactory.getLogger(PersonConsumer::class.java)

    @KafkaListener(topics = ["\${spring.kafka.consumer.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun processMessage(content: String) {
        var person: PersonMessage
        try {
            person = objectMapper.readValue(content, PersonMessage::class.java)
            log.info("Success to convert object: $person")
        } catch (e: JsonMappingException) {
            log.error("Error! Problem encountered in typing attributes in JSON object: ${e.message}")
        } catch (e: JsonProcessingException) {
            log.error("Error! Problem found in the structure of the JSON object: ${e.message}")
        }
    }

}