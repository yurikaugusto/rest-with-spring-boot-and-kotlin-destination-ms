package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain

interface FindPersonOutputPort {
    fun findById(id: Long, context: Context): PersonDomain
    fun findAll(context: Context): List<PersonDomain>
}