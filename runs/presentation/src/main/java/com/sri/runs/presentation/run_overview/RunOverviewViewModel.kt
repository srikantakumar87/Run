package com.sri.runs.presentation.run_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.core.data.runs.RunRepository
import com.sri.core.domain.runs.SyncRunScheduler
import com.sri.runs.presentation.run_overview.mapper.toRunUi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes

class RunOverviewViewModel(
    private val runRepository: RunRepository,
    private val syncRunScheduler: SyncRunScheduler
): ViewModel()  {

    var state by mutableStateOf(RunOverviewState())
        private set

    init{
        viewModelScope.launch {
            syncRunScheduler.scheduleSync(
                SyncRunScheduler.SyncType.FetchRuns(interval = 30.minutes)
            )

        }
        runRepository.getRuns().onEach{ runs ->
            val runsUi = runs.map { it.toRunUi() }
            state = state.copy(
                runs = runsUi
            )

        }.launchIn(viewModelScope)

        viewModelScope.launch {
            runRepository.syncPendingRuns()
            runRepository.fetchRuns()
        }
    }

    fun onAction(action: RunOverviewAction){
        when(action){
            RunOverviewAction.OnLogoutClick -> Unit
            RunOverviewAction.OnStartClick -> Unit
            is RunOverviewAction.DeleteRun -> {
                viewModelScope.launch {
                    runRepository.deleteRun(action.runUi.id)
                }
            }
            else -> Unit
        }
    }
}