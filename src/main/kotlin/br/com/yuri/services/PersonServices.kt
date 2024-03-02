package br.com.yuri.services

import br.com.yuri.exception.ResourceNotFoundException
import br.com.yuri.model.Person
import br.com.yuri.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServices {
    @Autowired
    var repository: PersonRepository? = null

    fun create(person: Person): Person {
        return repository!!.save(person)
    }

    fun findAll(): List<Person?> {
        return repository!!.findAll()
    }

    fun findById(id: Long): Person {
        return repository!!.findById(id).orElseThrow {
            ResourceNotFoundException(
                "No records found for this ID"
            )
        } as Person
    }

    fun update(person: Person): Person {
        val entity = repository!!.findById(person.id!!).orElseThrow {
            ResourceNotFoundException(
                "No records found for this ID"
            )
        } as Person
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return repository!!.save(entity)
    }

    fun delete(id: Long) {
        val entity = repository!!.findById(id).orElseThrow {
            ResourceNotFoundException(
                "No records found for this ID"
            )
        } as Person
        repository!!.delete(entity)
    }
}