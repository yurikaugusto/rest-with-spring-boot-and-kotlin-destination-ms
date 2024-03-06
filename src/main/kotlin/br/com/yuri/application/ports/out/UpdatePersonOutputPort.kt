package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.PersonDomain

interface UpdatePersonOutputPort {
    fun update(personDomain: PersonDomain): PersonDomain
}