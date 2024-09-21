package com.sri.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.text.input.TextFieldValue
import com.sri.auth.domain.PasswordValidationState

data class LoginState(
    val email: TextFieldValue = TextFieldValue(""),
    val isEmailValid: Boolean = false,
    val password: TextFieldValue = TextFieldValue(""),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,

    )
