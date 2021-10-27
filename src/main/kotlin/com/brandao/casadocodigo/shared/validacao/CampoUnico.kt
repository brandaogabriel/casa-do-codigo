package com.brandao.casadocodigo.shared.validacao


import com.brandao.casadocodigo.novacategoria.Categoria
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidadorCampoUnico::class])
annotation class CampoUnico(
    val message: String = "{com.brandao.casadocodigo.beanvalidation.campounico}",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val fieldName: String,
    val domainClass: KClass<*>
)

class ValidadorCampoUnico : ConstraintValidator<CampoUnico, Any>{

    var atributo: String? = null
    var klass: KClass<*>? = null

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun initialize(constraintAnnotation: CampoUnico) {
        atributo = constraintAnnotation.fieldName
        klass = constraintAnnotation.domainClass
    }

    override fun isValid(value: Any, p1: ConstraintValidatorContext): Boolean {
        val query: Query = entityManager.createQuery("SELECT 1 FROM " + klass!!.java.name + " WHERE " + atributo + " = :value")
        query.setParameter("value", value)
        val result = query.resultList
        return result.isEmpty()
    }

}