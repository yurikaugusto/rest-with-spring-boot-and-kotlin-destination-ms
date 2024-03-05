package br.com.yuri.deprecated.repository

import br.com.yuri.deprecated.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepositoryDeprecated : JpaRepository<Person, Long?>