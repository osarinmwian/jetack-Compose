package com.example.composelogin.use_case

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if(password.isBlank()){
            return  ValidationResult(
                successful = false,
                errorMessage = "password can't be blank"
            )
        }
        if (password.length < 6){
            return ValidationResult(
                successful = false,
                errorMessage = "password must contain at least 6 characters"
            )
        }
        val containsLettersAndDigit = password.any { it.isDigit() }
                && password.any { it.isLetter() }
        if(!containsLettersAndDigit){
            return  ValidationResult(
                successful = false,
                errorMessage = "password must contain at least 6 characters, one letter and digit"
            )
        }
        return  ValidationResult(
            successful = true
        )

    }
}