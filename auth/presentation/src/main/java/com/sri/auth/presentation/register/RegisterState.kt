package com.sri.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.sri.auth.domain.PasswordValidationState

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = true,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = passwordValidationState.isValidPassword && !isRegistering,

    )
