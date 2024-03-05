package br.com.yuri.adapters.out.repository

import br.com.yuri.adapters.out.repository.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonEntity, Long?>