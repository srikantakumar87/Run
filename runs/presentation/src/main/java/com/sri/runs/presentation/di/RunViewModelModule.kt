package com.sri.runs.presentation.di

import com.sri.runs.presentation.active_run.ActiveRunViewModel
import com.sri.runs.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}