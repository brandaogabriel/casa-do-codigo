package com.brandao.casadocodigo.shared.handler.validacao

import com.brandao.casadocodigo.novoautor.AutorRepository
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidadorEmailUnido::class])
annotation class EmailUnico(
    val message: String = "Já existe um autor com esse e-mail!",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

class ValidadorEmailUnido(
    val autorRepository: AutorRepository
) : ConstraintValidator<EmailUnico, String> {


    override fun isValid(
        value: String?,
        context: ConstraintValidatorContext?
    ): Boolean {

        //ja deve ser validado pela anotacao @NotNull
        if (value.isNullOrBlank()) {
            return false
        }

        val possivelAutor = autorRepository.findByEmail(value)
        return possivelAutor.isEmpty
    }

}