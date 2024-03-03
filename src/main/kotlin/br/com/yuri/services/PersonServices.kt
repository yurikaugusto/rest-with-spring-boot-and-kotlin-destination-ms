package br.com.yuri.services

import br.com.yuri.exception.ResourceNotFoundException
import br.com.yuri.model.Person
import br.com.yuri.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServices(private val repository: PersonRepository) {

    fun create(person: Person): Person = repository.save(person)

    fun findAll(): List<Person> = repository.findAll()

    fun findById(id: Long): Person = repository.findById(id)
        .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    fun update(person: Person): Person {
//        person.id!! "!!" force operator, asserts that an expression is non-nullable
//        this operator states that the id value is not null, if so, when executing the application
//        a NullPointerException will be thrown
        val entity = findById(person.id!!)
        person.id = entity.id
        return repository.save(entity)
    }

    fun delete(id: Long) {
        val person = findById(id)
        repository.delete(person)
    }
}
