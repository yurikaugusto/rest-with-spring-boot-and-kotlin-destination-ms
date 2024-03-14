package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.PersonDomain
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.`in`.UpdatePersonInputPort
import br.com.yuri.application.ports.out.UpdatePersonOutputPort

class UpdatePersonUseCase(
    private val findPersonInputPort: FindPersonInputPort,
    private val updatePersonOutputPort: UpdatePersonOutputPort
) : UpdatePersonInputPort {

    override fun update(personDomain: PersonDomain, context: Context): PersonDomain {
        findPersonInputPort.findById(personDomain.id!!, context)
        val updatedPerson: PersonDomain = updatePersonOutputPort.update(personDomain, context)
        return updatedPerson
    }
}