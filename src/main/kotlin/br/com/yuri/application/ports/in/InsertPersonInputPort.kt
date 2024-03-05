package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.PersonDomain

interface InsertPersonInputPort {
    fun create(personDomain: PersonDomain): PersonDomain
}