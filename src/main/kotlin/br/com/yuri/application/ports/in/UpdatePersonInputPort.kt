package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain

interface UpdatePersonInputPort {
    fun update(personDomain: PersonDomain, context: Context): PersonDomain
}