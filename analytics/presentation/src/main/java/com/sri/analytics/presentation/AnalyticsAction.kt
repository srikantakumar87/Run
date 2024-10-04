package com.sri.analytics.presentation

sealed interface AnalyticsAction {

    data object OnBackClick : AnalyticsAction
}