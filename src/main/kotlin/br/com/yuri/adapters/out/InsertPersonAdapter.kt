package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.adapters.out.repository.entity.PersonEntity
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.InsertPersonOutputPort
import org.springframework.stereotype.Component

@Component
class InsertPersonAdapter(
    private val repository: PersonRepository
) : InsertPersonOutputPort {

    override fun create(personDomain: PersonDomain): PersonDomain {
        val personEntity = repository.save(PersonEntity(null, personDomain.firstName, personDomain.lastName, personDomain.address, personDomain.gender))
        return PersonDomain(personEntity.id, personEntity.firstName, personEntity.lastName, personEntity.address, personEntity.gender)
    }
}