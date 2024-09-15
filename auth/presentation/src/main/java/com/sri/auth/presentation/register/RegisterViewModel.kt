@file:OptIn(ExperimentalComposeUiApi::class)

package com.sri.auth.presentation.register


import androidx.activity.result.launch
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateObserver

import androidx.compose.ui.semantics.text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.auth.domain.UserDataValidator
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterViewModel(private val userDataValidator: UserDataValidator): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    init{
        viewModelScope.launch {
            snapshotFlow { state.email.text }
                .onEach { email ->
                    state = state.copy(
                        isEmailValid = userDataValidator.isValidEmail(email.toString())
                    )
                }
            snapshotFlow { state.password.text }
                .onEach { password ->
                    state = state.copy(
                        passwordValidationState = userDataValidator.validatePassword(password.toString())
                    )
                }

        }

    }
    fun onAction(action: RegisterAction){

    }
}




