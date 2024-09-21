@file:OptIn(ExperimentalComposeUiApi::class)

package com.sri.auth.presentation.register


import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.text.TextRange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.auth.domain.UserDataValidator
import androidx.compose.ui.text.input.TextFieldValue
import androidx.room.util.copy
import com.sri.auth.domain.AuthRepository
import com.sri.auth.domain.PasswordValidationState
import com.sri.auth.presentation.R
import com.sri.core.domain.util.DataError
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.sri.core.domain.util.Result
import com.sri.core.presentation.ui.UiText
import com.sri.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            launch {
                snapshotFlow { state.email.text }
                    .onEach { email ->
                        val isValidEmail = userDataValidator.isValidEmail(email)
                        println("isValidEmail: $isValidEmail")
                        state = state.copy(isEmailValid = isValidEmail)
                    }
            }

            launch {
                snapshotFlow { state.password.text }
                    .onEach { password ->
                        state = state.copy(
                            passwordValidationState = userDataValidator.validatePassword(password.toString())
                        )
                    }
            }
        }
    }

    // Simplified email update logic to prevent recomposition issues
    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnEmailChanged -> {

                val newEmail = action.email
                val isValidEmail =
                    userDataValidator.isValidEmail(newEmail)  // Validate email after change
                println("isValidEmail: $isValidEmail")

                state = state.copy(
                    email = TextFieldValue(
                        text = action.email,
                        selection = TextRange(action.email.length)  // Move cursor to end of the text
                    ),
                    isEmailValid = isValidEmail
                )
            }


            RegisterAction.OnRegisterClick -> register()



            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            is RegisterAction.OnPasswordChanged -> {
                val newPassword = action.password
                val isValidPassword =
                    userDataValidator.validatePassword(newPassword) // Validate email after change
                println("isValidPassword: $isValidPassword")

                println(newPassword)
                val passwordValidationState =
                    userDataValidator.validatePassword(newPassword.toString())
                state = state.copy(
                    passwordValidationState = passwordValidationState,
                    //passwordValidationState.isValidPassword = true,
                    canRegister = state.isEmailValid && passwordValidationState.isValidPassword && !state.isRegistering,
                    password = TextFieldValue(
                        text = action.password,
                        selection = TextRange(action.password.length)  // Move cursor to end of the text
                    ),

                    )

            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)
            when(result){
               is Result.Error -> {
                   if(result.error == DataError.Network.CONFLICT){
                       eventChannel.send(RegisterEvent.Error(
                           UiText.StringResource(R.string.error_email_exists)
                       ))



                   }
                   eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
               }
               is Result.Success -> {

                   eventChannel.send(RegisterEvent.RegistrationSuccess)

               }
            }

        }
    }
}



