package com.sri.runs.presentation.run_overview

import com.sri.runs.presentation.run_overview.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)
