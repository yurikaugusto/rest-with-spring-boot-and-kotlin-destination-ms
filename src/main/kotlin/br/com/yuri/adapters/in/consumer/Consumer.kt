package br.com.yuri.adapters.`in`.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PersonConsumer {

    @KafkaListener(topics = ["\${spring.kafka.consumer.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun processMessage(content: String) {
        val any = content
        println(content)
    }

}