package com.brandao.casadocodigo.shared.handler


import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CapturaExcecoes(
    val messageSource: MessageSource,
) {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun beanException(exception: MethodArgumentNotValidException): List<ErrorResponse> {
        val errors = mutableListOf<ErrorResponse>()

        val fieldErrors = exception.bindingResult.fieldErrors
        fieldErrors.forEach { error ->
            val message = messageSource.getMessage(error, LocaleContextHolder.getLocale())
            val errorResponse = ErrorResponse(campo = error.field, erro = message)
            errors.add(errorResponse)
        }

        return errors
    }
}