package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.out.FindPersonOutputPort

class FindPersonUseCase(
    private val findPersonOutputPort: FindPersonOutputPort
) : FindPersonInputPort {

    override fun findById(id: Long): PersonDomain {
        return findPersonOutputPort.findById(id)
    }

    override fun findAll(): List<PersonDomain> {
        return findPersonOutputPort.findAll()
    }
}