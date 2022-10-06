package com.example.composelogin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.model.LoginFormEvent
import com.example.composelogin.model.LoginFormState
import com.example.composelogin.use_case.ValidateEmail
import com.example.composelogin.use_case.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private  val validateEmail: ValidateEmail = ValidateEmail(),
    private  val validatePassword: ValidatePassword = ValidatePassword()
): ViewModel() {
    var state by mutableStateOf(LoginFormState())

    private  var validationEventChannel = Channel<ValidationEvent>()
    var validationEvents =validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent){
        when(event){
            is LoginFormEvent.EmailChanged ->{
                 state = state.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged ->{
                state = state.copy( password  = event.password)

            }
            is LoginFormEvent.Login ->{
               loginData()
            }
        }

    }

    private fun loginData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any{ !it.successful}

        if (hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}