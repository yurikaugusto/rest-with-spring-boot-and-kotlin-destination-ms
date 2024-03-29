package br.com.yuri.controller

import br.com.yuri.model.Person
import br.com.yuri.services.PersonServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(private val service: PersonServices) {

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