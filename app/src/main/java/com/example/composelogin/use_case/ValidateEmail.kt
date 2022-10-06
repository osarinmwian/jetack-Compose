package com.example.composelogin.use_case

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if(email.isBlank()){
           return  ValidationResult(
               successful = false,
               errorMessage = "email can't be blank"
           )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "invalid email format"
            )
        }
        return  ValidationResult(
            successful = true
        )
    }
}