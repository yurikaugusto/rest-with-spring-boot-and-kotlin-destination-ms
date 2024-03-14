package br.com.yuri.adapters.out

import br.com.yuri.adapters.`in`.consumer.exception.PersonNotFoundKafkaException
import br.com.yuri.adapters.`in`.controller.exception.PersonNotFoundRestException
import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.out.FindPersonOutputPort
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class FindPersonAdapter(
    private val repository: PersonRepository
) : FindPersonOutputPort {
    override fun findById(id: Long, context: Context): PersonDomain {
        val personEntity = repository.findById(id).getOrNull() ?: when (context) {
            Context.REST -> throw PersonNotFoundRestException("Person with id: $id not found")
            Context.KAKFA -> throw PersonNotFoundKafkaException("Person with id: $id not found")
        }
        return PersonDomain(
            personEntity.id,
            personEntity.firstName,
            personEntity.lastName,
            personEntity.address,
            personEntity.gender
        )
    }

    override fun findAll(context: Context): List<PersonDomain> {
        val entityList = repository.findAll()
        val personDomainList = entityList.map { PersonDomain(it.id, it.firstName, it.lastName, it.address, it.gender) }
        return personDomainList
    }
}