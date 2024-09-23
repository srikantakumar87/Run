package com.sri.runs.presentation.active_run

import com.sri.core.presentation.ui.UiText

sealed interface ActiveRunEvent {
    data class Error(val message: UiText) : ActiveRunEvent
    data object RunSaved : ActiveRunEvent
    data object Success : ActiveRunEvent

}