package com.sri.runs.presentation.active_run

import android.location.Location
import com.sri.runs.domain.RunData
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class ActiveRunState(
    val elapsedTime: Duration = Duration.ZERO,
    val runData: RunData = RunData(),
    val shouldTrack: Boolean = false,
    val hasStartedRunning: Boolean = false,
    val currentLocation: Location? = null,
    val isRunFinished: Boolean = false,
    val isRunSaved: Boolean = false,
    val isSavingRun: Boolean = false

)
