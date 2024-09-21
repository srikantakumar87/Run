package com.sri.auth.presentation.login

import com.sri.auth.presentation.register.RegisterAction

sealed interface LoginAction {
    data object OnTogglePasswordVisibilityClick : LoginAction
    data object OnLoginClick : LoginAction
    data object OnRegisterClick : LoginAction
    data class OnEmailChanged(val email: String) : LoginAction
    data class OnPasswordChanged(val password: String) : LoginAction

}