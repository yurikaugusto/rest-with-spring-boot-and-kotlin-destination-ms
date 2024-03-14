package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.ports.`in`.DeletePersonInputPort
import br.com.yuri.application.ports.`in`.FindPersonInputPort
import br.com.yuri.application.ports.out.DeletePersonOutpuPort

class DeletePersonUseCase(
    private val findPersonInputPort: FindPersonInputPort,
    private val deletePersonOutpuPort: DeletePersonOutpuPort
) : DeletePersonInputPort {

    override fun deleteById(id: Long, context: Context) {
        findPersonInputPort.findById(id, context)
        deletePersonOutpuPort.deleteById(id, context)
    }
}