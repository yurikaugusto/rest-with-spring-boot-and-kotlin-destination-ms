package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.Context

interface DeletePersonInputPort {
    fun deleteById(id: Long, context: Context)
}