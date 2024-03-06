package br.com.yuri.config

import br.com.yuri.adapters.out.DeletePersonAdapter
import br.com.yuri.application.core.usecase.DeletePersonUseCase
import br.com.yuri.application.core.usecase.FindPersonUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DeletePersonConfig {
    @Bean
    fun deletePersonUseCaseNean(
        findPersonUseCase: FindPersonUseCase,
        deletePersonAdapter: DeletePersonAdapter
    ): DeletePersonUseCase = DeletePersonUseCase(findPersonUseCase, deletePersonAdapter)
}