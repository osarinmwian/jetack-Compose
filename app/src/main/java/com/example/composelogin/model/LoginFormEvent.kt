package com.example.composelogin.model

sealed class LoginFormEvent {
    data class EmailChanged(val email: String): LoginFormEvent()
    data class PasswordChanged(val password: String): LoginFormEvent()

    object Login: LoginFormEvent()
}