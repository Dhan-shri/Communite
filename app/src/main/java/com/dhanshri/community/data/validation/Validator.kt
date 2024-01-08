package com.dhanshri.community.data.validation

object Validator {

    fun validateFirstName(firstName: String): ValidationResponse {
        return ValidationResponse(
            (!firstName.isNullOrEmpty() && firstName.length >= 4)
        )
    }

    fun validateLastName(lastName: String) : ValidationResponse {
        return ValidationResponse(
            (!lastName.isNullOrEmpty() && lastName.length >= 2)
        )
    }
    fun validateEmail(email: String): ValidationResponse {
        return ValidationResponse(
            (!email.isNullOrEmpty())
        )
    }
    fun validatePassword(password: String): ValidationResponse {
        return ValidationResponse(
            (!password.isNullOrEmpty() && password.length >= 6)
        )
    }
}

data class ValidationResponse(
    val isValid: Boolean = false,
)