package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.adapters.out.repository.entity.PersonEntity
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.UpdatePersonOutputPort
import br.com.yuri.config.GenericLogger
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class UpdatePersonAdapter(
    private val repository: PersonRepository
) : UpdatePersonOutputPort {

    private val log: Logger = GenericLogger.loggerFor(UpdatePersonAdapter::class.java)

    override fun update(personDomain: PersonDomain, context: Context): PersonDomain {
        log.info("Updating person in database: $personDomain, context: $context")
        val personSaved = repository.save(
            PersonEntity(
                personDomain.id,
                personDomain.firstName,
                personDomain.lastName,
                personDomain.address,
                personDomain.gender
            )
        )
        log.info("Operation carried out successfully")
        return PersonDomain(
            personSaved.id,
            personSaved.firstName,
            personSaved.lastName,
            personSaved.address,
            personSaved.gender
        )
    }
}