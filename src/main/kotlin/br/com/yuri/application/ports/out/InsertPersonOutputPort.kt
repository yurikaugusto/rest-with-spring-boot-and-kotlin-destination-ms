package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain

interface InsertPersonOutputPort {
    fun create(personDomain: PersonDomain, context: Context): PersonDomain
}