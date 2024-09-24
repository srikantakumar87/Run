package com.sri.runs.presentation.di

import com.sri.runs.presentation.active_run.ActiveRunViewModel
import com.sri.runs.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.sri.runs.domain.RunningTracker

val RunPresentationModule = module {
    singleOf(::RunningTracker)
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)

}