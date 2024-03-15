package br.com.yuri.adapters.out

import br.com.yuri.adapters.out.repository.PersonRepository
import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.ports.out.DeletePersonOutpuPort
import br.com.yuri.config.GenericLogger
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class DeletePersonAdapter(
    private val repository: PersonRepository
) : DeletePersonOutpuPort {

    private val log: Logger = GenericLogger.loggerFor(DeletePersonAdapter::class.java)

    override fun deleteById(id: Long, context: Context) {
        log.info("Deleting person with id: $id, context: $context")
        repository.deleteById(id)
        log.info("Operation carried out successfully")
    }
}