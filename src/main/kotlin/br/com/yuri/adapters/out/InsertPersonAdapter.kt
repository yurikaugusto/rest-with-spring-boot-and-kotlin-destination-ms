package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.adapters.out.repository.entity.PersonEntity
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.InsertPersonOutputPort
import br.com.yuri.config.GenericLogger
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class InsertPersonAdapter(
    private val repository: PersonRepository
) : InsertPersonOutputPort {

    private val log: Logger = GenericLogger.loggerFor(InsertPersonAdapter::class.java)

    override fun create(personDomain: PersonDomain, context: Context): PersonDomain {
        log.info("Creating person in the database: $personDomain, context: $context")
        val personEntity = repository.save(
            PersonEntity(
                null,
                personDomain.firstName,
                personDomain.lastName,
                personDomain.address,
                personDomain.gender
            )
        )
        log.info("Operation carried out successfully")
        return PersonDomain(
            personEntity.id,
            personEntity.firstName,
            personEntity.lastName,
            personEntity.address,
            personEntity.gender
        )
    }
}