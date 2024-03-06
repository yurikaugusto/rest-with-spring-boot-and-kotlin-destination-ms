package br.com.yuri.adapters.`in`.controller

import br.com.yuri.adapters.`in`.controller.request.PersonRequest
import br.com.yuri.adapters.`in`.controller.response.PersonResponse
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.`in`.InsertPersonInputPort
import br.com.yuri.deprecated.model.Person
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v2/person")
class PersonController(
    private val insertPersonInputPort: InsertPersonInputPort,
    private val findPersonInputPort: FindPersonInputPort
) {

    @GetMapping
    fun findAll(): List<PersonResponse> {
        val listPerson = findPersonInputPort.findAll()
        val personResponseList = listPerson.map { PersonResponse(it.id, it.firstName, it.lastName, it.address, it.gender) }
        return personResponseList
    }

    @GetMapping("{id}")
    fun findById(@PathVariable("id") id: Long): PersonResponse {
        val personDomain = findPersonInputPort.findById(id)
        return PersonResponse(personDomain.id, personDomain.firstName, personDomain.lastName, personDomain.address, personDomain.gender)
    }


    @PostMapping
    fun create(@RequestBody personRequest: PersonRequest): PersonResponse {
        val personDomain = insertPersonInputPort.create(PersonDomain(null, personRequest.firstName, personRequest.lastName, personRequest.address,personRequest.gender))
        return PersonResponse(personDomain.id, personDomain.firstName, personDomain.lastName, personDomain.address,personDomain.gender)
    }

    @PutMapping
    fun update(@RequestBody person: Person): Person = TODO("NOT IMPLEMENTED")


    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        TODO("NOT IMPLEMENTED")
    }

}