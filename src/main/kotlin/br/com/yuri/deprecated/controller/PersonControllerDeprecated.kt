package br.com.yuri.deprecated.controller

import br.com.yuri.deprecated.model.Person
import br.com.yuri.deprecated.services.PersonServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/person")
class PersonControllerDeprecated(private val service: PersonServices) {

    @GetMapping
    fun findAll(): List<Person> {
        return service.findAll()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable("id") id: Long): Person = service.findById(id)

    @PostMapping
    fun create(@RequestBody person: Person): Person = service.create(person)

    @PutMapping
    fun update(@RequestBody person: Person): Person = service.update(person)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}