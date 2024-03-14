package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain

interface InsertPersonInputPort {
    fun create(personDomain: PersonDomain, context: Context): PersonDomain
}