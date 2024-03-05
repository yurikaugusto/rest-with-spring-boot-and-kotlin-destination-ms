package br.com.yuri.config

import br.com.yuri.adapters.out.InsertPersonAdapter
import br.com.yuri.application.core.usecase.InsertPersonUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InsertPersonConfig {
    @Bean
    fun insertPersonUseCaseBean(insertPersonAdapter: InsertPersonAdapter): InsertPersonUseCase =
        InsertPersonUseCase(insertPersonAdapter)

}