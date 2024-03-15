package br.com.yuri.application.ports.out

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.MessageDomain

interface PublishMessageOutputPort {
    fun send(message: MessageDomain, context: Context)
}