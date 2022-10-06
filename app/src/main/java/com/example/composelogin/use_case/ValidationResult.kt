package com.example.composelogin.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)