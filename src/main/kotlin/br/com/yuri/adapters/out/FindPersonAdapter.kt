package br.com.yuri.adapters.out

import br.com.yuri.adapters.`in`.consumer.exception.PersonNotFoundKafkaException
import br.com.yuri.adapters.`in`.controller.exception.PersonNotFoundRestException
import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.FindPersonOutputPort
import br.com.yuri.config.GenericLogger
import org.slf4j.Logger
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class FindPersonAdapter(
    private val repository: PersonRepository
) : FindPersonOutputPort {

    private val log: Logger = GenericLogger.loggerFor(FindPersonAdapter::class.java)

    override fun findById(id: Long, context: Context): PersonDomain {
        log.info("Searching person with id: $id, context: $context")
        val errorMessage = "Person with id: $id not found"
        val personEntity = repository.findById(id).getOrNull() ?: when (context) {
            Context.REST -> {
                log.error(errorMessage)
                throw PersonNotFoundRestException(errorMessage)
            }

            Context.KAKFA -> {
                log.error(errorMessage)
                throw PersonNotFoundKafkaException(errorMessage)
            }
        }
        log.info("Operation carried out successfully")
        return PersonDomain(
            personEntity.id,
            personEntity.firstName,
            personEntity.lastName,
            personEntity.address,
            personEntity.gender
        )
    }

    override fun findAll(context: Context): List<PersonDomain> {
        log.info("Searching all registered people, context: $context")
        val entityList = repository.findAll()
        log.info("Operation carried out successfully")
        val personDomainList = entityList.map { PersonDomain(it.id, it.firstName, it.lastName, it.address, it.gender) }
        return personDomainList
    }
}