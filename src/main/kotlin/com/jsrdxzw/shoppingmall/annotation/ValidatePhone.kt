package com.jsrdxzw.shoppingmall.annotation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * @author  xuzhiwei
 * @date  2020/09/01
 */
@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PhoneConstraintValidator::class])
annotation class ValidatePhone(
        val message: String = "不是一个合法的电话",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class PhoneConstraintValidator : ConstraintValidator<ValidatePhone, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) return false
        return PHONE_REGEX.containsMatchIn(value)
    }

    companion object {
        private val PHONE_REGEX = Regex("^1[3-9]\\d{9}$")
    }
}
