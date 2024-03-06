package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.PersonDomain

interface FindPersonOutputPort {
    fun findById(id: Long): PersonDomain
}