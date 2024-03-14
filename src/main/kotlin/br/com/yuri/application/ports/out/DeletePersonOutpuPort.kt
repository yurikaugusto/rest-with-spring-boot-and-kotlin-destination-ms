package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.Context

interface DeletePersonOutpuPort {
    fun deleteById(id: Long, context: Context)
}