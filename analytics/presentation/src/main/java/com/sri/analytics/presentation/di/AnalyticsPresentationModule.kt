package com.sri.analytics.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.sri.analytics.presentation.AnalyticsDashboardViewModel

val analyticsPresentationModule = module {
    viewModelOf(::AnalyticsDashboardViewModel)

}