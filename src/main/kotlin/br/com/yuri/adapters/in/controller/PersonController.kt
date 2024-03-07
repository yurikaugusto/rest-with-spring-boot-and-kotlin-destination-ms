package br.com.yuri.adapters.`in`.controller

import br.com.yuri.adapters.`in`.controller.request.PersonRequest
import br.com.yuri.adapters.`in`.controller.response.PersonResponse
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.DeletePersonInputPort
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.`in`.InsertPersonInputPort
import br.com.yuri.application.ports.`in`.UpdatePersonInputPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v2/person")
class PersonController(
    private val insertPersonInputPort: InsertPersonInputPort,
    private val findPersonInputPort: FindPersonInputPort,
    private val updatePersonInputPort: UpdatePersonInputPort,
    private val deletePersonInputPort: DeletePersonInputPort
) {

    @PostMapping
    fun create(@RequestBody personRequest: PersonRequest): ResponseEntity<*> {
        val personDomain = insertPersonInputPort.create(
            PersonDomain(
                null,
                personRequest.firstName,
                personRequest.lastName,
                personRequest.address,
                personRequest.gender
            )
        )
        return ResponseEntity(
            PersonResponse(
                personDomain.id,
                personDomain.firstName,
                personDomain.lastName,
                personDomain.address,
                personDomain.gender
            ), HttpStatus.CREATED
        )
    }

    @GetMapping
    fun findAll(): ResponseEntity<*> {
        val listPerson = findPersonInputPort.findAll()
        val personResponseList =
            listPerson.map { PersonResponse(it.id, it.firstName, it.lastName, it.address, it.gender) }
        return ResponseEntity(personResponseList, HttpStatus.OK)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<*> {
        val personDomain = findPersonInputPort.findById(id)
        return ResponseEntity(
            PersonResponse(
                personDomain.id,
                personDomain.firstName,
                personDomain.lastName,
                personDomain.address,
                personDomain.gender
            ), HttpStatus.OK
        )
    }

    @PutMapping
    fun update(@RequestBody person: PersonRequest): ResponseEntity<*> {
        val updatedPerson: PersonDomain = updatePersonInputPort.update(
            PersonDomain(
                person.id,
                person.firstName,
                person.lastName,
                person.address,
                person.gender
            )
        )
        return ResponseEntity(
            PersonResponse(
                updatedPerson.id,
                updatedPerson.firstName,
                updatedPerson.lastName,
                updatedPerson.address,
                updatedPerson.gender
            ), HttpStatus.OK
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        deletePersonInputPort.deleteById(id)
        return ResponseEntity(null, HttpStatus.NO_CONTENT)
    }
}