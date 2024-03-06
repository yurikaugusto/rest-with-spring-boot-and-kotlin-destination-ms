package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.InsertPersonInputPort
import br.com.yuri.application.ports.out.InsertPersonOutputPort

class InsertPersonUseCase(
    private val insertPersonOutputPort: InsertPersonOutputPort
) : InsertPersonInputPort {
    override fun create(personDomain: PersonDomain): PersonDomain {
        return insertPersonOutputPort.create(personDomain)
    }
}