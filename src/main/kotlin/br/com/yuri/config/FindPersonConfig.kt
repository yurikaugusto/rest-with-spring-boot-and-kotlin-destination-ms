package br.com.yuri.config

import br.com.yuri.adapters.out.FindPersonAdapter
import br.com.yuri.application.core.usecase.FindPersonUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FindPersonConfig {
    @Bean
    fun findPersonUseCaseBean(findPersonAdapter: FindPersonAdapter): FindPersonUseCase =
        FindPersonUseCase(findPersonAdapter)
}