package br.com.yuri.config

import br.com.yuri.adapters.out.PublishMessageAdapter
import br.com.yuri.application.core.usecase.PublishMessageUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PublishMessageConfig {
    @Bean
    fun publishMessageUseCaseBean(publishMessageAdapter: PublishMessageAdapter) =
        PublishMessageUseCase(publishMessageAdapter)
}