package br.com.yuri.adapters.`in`.controller.exception.handler

import br.com.yuri.adapters.`in`.controller.exception.PersonNotFoundException
import br.com.yuri.adapters.`in`.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RestControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException::class)
    fun handlePersonNotFoundException(exception: PersonNotFoundException, request: WebRequest): ErrorResponse =
        ErrorResponse(
            getCurrentDateWithTimeZone(),
            exception.message ?: "Unknown error",
            request.getDescription(false)
        )

    private fun getCurrentDateWithTimeZone(): String {
        val timeNow = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Sao_Paulo"))
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        val timeNowAsString: String = timeNow.format(pattern)
        return timeNowAsString
    }
}