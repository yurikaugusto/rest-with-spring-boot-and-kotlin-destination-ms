package br.com.yuri.adapters.`in`.consumer

import br.com.yuri.adapters.`in`.consumer.exception.InvalidMessageException
import br.com.yuri.adapters.`in`.consumer.exception.PersonNotFoundKafkaException
import br.com.yuri.adapters.`in`.consumer.message.OperationType
import br.com.yuri.adapters.`in`.consumer.message.PersonMessage
import br.com.yuri.adapters.`in`.consumer.message.toPersonDomain
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.ports.`in`.DeletePersonInputPort
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.`in`.InsertPersonInputPort
import br.com.yuri.application.ports.`in`.UpdatePersonInputPort
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PersonConsumer(
    private val objectMapper: ObjectMapper,
    private val insertPersonInputPort: InsertPersonInputPort,
    private val findPersonInputPort: FindPersonInputPort,
    private val updatePersonInputPort: UpdatePersonInputPort,
    private val deletePersonInputPort: DeletePersonInputPort
) {
    private val log = LoggerFactory.getLogger(PersonConsumer::class.java)

    @KafkaListener(topics = ["\${spring.kafka.consumer.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun processMessage(content: String) {
        try {
            val personMessage = objectMapper.readValue(content, PersonMessage::class.java)
            log.info("Success to convert object: $personMessage")
            performAction(personMessage)
        } catch (e: JsonMappingException) {
            log.error("Error! Problem encountered in typing attributes in JSON object: ${e.message}")
        } catch (e: JsonProcessingException) {
            log.error("Error! Problem found in the structure of the JSON object: ${e.message}")
        }
    }

    fun performAction(personMessage: PersonMessage) {
        val operationType = personMessage.operationType.uppercase()
        log.info("The received message will perform operation: $operationType")
        try {
            when (OperationType.valueOf(operationType)) {
                OperationType.CREATE -> {
                    personMessage.id = null
                    val create = insertPersonInputPort.create(personMessage.toPersonDomain(), Context.KAKFA)
                    log.info("create: $create")
                }

                OperationType.READ -> {
                    if (personMessage.id == null) throw InvalidMessageException("Unable to search because field 'id' is null")
                    val findById = findPersonInputPort.findById(personMessage.id!!, Context.KAKFA)
                    log.info("findById: $findById")
                }

                OperationType.UPDATE -> {
                    val personDomain = personMessage.toPersonDomain()
                    if (personDomain.id == null) throw InvalidMessageException("Unable to update because 'id' field is null")
                    val update = updatePersonInputPort.update(personDomain, Context.KAKFA)
                    log.info("update: $update")
                }

                OperationType.DELETE -> {
                    if (personMessage.id == null) throw InvalidMessageException("Unable to delete because 'id' field is null")
                    deletePersonInputPort.deleteById(personMessage.id!!, Context.KAKFA)
                    log.info("Successfully deleted")
                }
            }
        } catch (ex: IllegalArgumentException) {
            log.error("Error! This message does not have a valid operationType: ${ex.message}")
        } catch (ex: InvalidMessageException) {
            log.error("Error! An error occurred while processing the message: ${ex.message}")
        } catch (ex: PersonNotFoundKafkaException) {
            log.error("Error! There is no person registered with the specified ID: ${ex.message}")
        } catch (ex: Exception) {
            log.error("Error! An error occurred: ${ex.message}")
        }
    }
}