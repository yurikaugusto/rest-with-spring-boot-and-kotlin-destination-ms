package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.application.ports.out.DeletePersonOutpuPort
import org.springframework.stereotype.Component

@Component
class DeletePersonAdapter(
    private val repository: PersonRepository
) : DeletePersonOutpuPort {

    override fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}