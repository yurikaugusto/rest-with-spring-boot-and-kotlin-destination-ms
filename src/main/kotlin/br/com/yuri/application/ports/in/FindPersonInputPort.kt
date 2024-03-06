package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.PersonDomain

interface FindPersonInputPort {
    fun findById(id: Long): PersonDomain
    fun findAll(): List<PersonDomain>
}