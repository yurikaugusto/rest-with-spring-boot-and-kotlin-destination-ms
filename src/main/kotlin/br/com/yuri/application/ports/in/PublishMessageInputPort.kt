package br.com.yuri.application.ports.`in`

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.MessageDomain

interface PublishMessageInputPort {
    fun send(message: MessageDomain, context: Context)
}