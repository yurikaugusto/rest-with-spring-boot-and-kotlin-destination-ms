package br.com.yuri.application.core.usecase

import br.com.yuri.application.core.domain.Context
import br.com.yuri.application.core.domain.MessageDomain
import br.com.yuri.application.ports.`in`.PublishMessageInputPort
import br.com.yuri.application.ports.out.PublishMessageOutputPort

class PublishMessageUseCase(
    private val publishMessageOutputPort: PublishMessageOutputPort
) : PublishMessageInputPort {

    override fun send(message: MessageDomain, context: Context) {
        publishMessageOutputPort.send(message, context)
    }
}