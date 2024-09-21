package com.sri.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.auth.domain.AuthRepository
import com.sri.auth.domain.UserDataValidator
import com.sri.auth.presentation.intro.IntroAction
import com.sri.auth.presentation.register.RegisterAction
import com.sri.auth.presentation.register.RegisterEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
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

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClick -> TODO()
            is LoginAction.OnEmailChanged -> {

                val newEmail = action.email
                val isValidEmail = userDataValidator.isValidEmail(newEmail)  // Validate email after change
               // println("isValidEmail: $isValidEmail")

                state = state.copy(
                    email = TextFieldValue(
                        text = action.email,
                        selection = TextRange(action.email.length)  // Move cursor to end of the text
                    ),
                    isEmailValid = isValidEmail
                )
            }
            is LoginAction.OnPasswordChanged -> {

                val newPassword = action.password
                //val isValidEmail = userDataValidator.isValidEmail(newEmail)  // Validate email after change
                // println("isValidEmail: $isValidEmail")

                state = state.copy(
                    password = TextFieldValue(
                        text = action.password,
                        selection = TextRange(action.password.length)  // Move cursor to end of the text
                    ),
                    //isEmailValid = isValidEmail
                    canLogin = true
                )
            }
            LoginAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }

    }
}