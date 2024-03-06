package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.`in`.UpdatePersonInputPort
import br.com.yuri.application.ports.out.UpdatePersonOutputPort

class UpdatePersonUseCase(
    private val findPersonInputPort: FindPersonInputPort,
    private val updatePersonOutputPort: UpdatePersonOutputPort
) : UpdatePersonInputPort {

    override fun update(personDomain: PersonDomain): PersonDomain {
        findPersonInputPort.findById(personDomain.id!!)
        val updatedPerson: PersonDomain = updatePersonOutputPort.update(personDomain)
        return updatedPerson
    }
}