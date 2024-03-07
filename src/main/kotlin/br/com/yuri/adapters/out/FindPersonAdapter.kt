package br.com.yuri.adapters.out

import br.com.yuri.adapters.`in`.controller.exception.PersonNotFoundException
import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.FindPersonOutputPort
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class FindPersonAdapter(
    private val repository: PersonRepository
) : FindPersonOutputPort {
    override fun findById(id: Long): PersonDomain {
        val personEntity =
            repository.findById(id).getOrNull() ?: throw PersonNotFoundException("Person with id: $id not found")
        return PersonDomain(
            personEntity.id,
            personEntity.firstName,
            personEntity.lastName,
            personEntity.address,
            personEntity.gender
        )
    }

    override fun findAll(): List<PersonDomain> {
        val entityList = repository.findAll()
        val personDomainList = entityList.map { PersonDomain(it.id, it.firstName, it.lastName, it.address, it.gender) }
        return personDomainList
    }
}