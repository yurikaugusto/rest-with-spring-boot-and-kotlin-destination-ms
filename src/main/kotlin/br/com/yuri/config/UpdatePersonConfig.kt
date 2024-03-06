package br.com.yuri.config

import br.com.yuri.adapters.out.UpdatePersonAdapter
import br.com.yuri.application.core.usecase.FindPersonUseCase
import br.com.yuri.application.core.usecase.UpdatePersonUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UpdatePersonConfig {
    @Bean
    fun updatePersonUseCaseBean(
        findPersonUseCase: FindPersonUseCase,
        updatePersonAdapter: UpdatePersonAdapter
    ): UpdatePersonUseCase = UpdatePersonUseCase(findPersonUseCase, updatePersonAdapter)
}