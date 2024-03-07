package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.adapters.out.repository.entity.PersonEntity
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.UpdatePersonOutputPort
import org.springframework.stereotype.Component

@Component
class UpdatePersonAdapter(
    private val repository: PersonRepository
) : UpdatePersonOutputPort {

    override fun update(personDomain: PersonDomain): PersonDomain {
        val personSaved = repository.save(
            PersonEntity(
                personDomain.id,
                personDomain.firstName,
                personDomain.lastName,
                personDomain.address,
                personDomain.gender
            )
        )
        return PersonDomain(
            personSaved.id,
            personSaved.firstName,
            personSaved.lastName,
            personSaved.address,
            personSaved.gender
        )
    }
}