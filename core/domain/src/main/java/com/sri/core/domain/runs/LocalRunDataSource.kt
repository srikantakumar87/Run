package com.sri.core.domain.runs

import com.sri.core.data.runs.Run
import com.sri.core.domain.util.DataError
import com.sri.core.domain.util.Result
import kotlinx.coroutines.flow.Flow


typealias RunId = String

interface LocalRunDataSource {
    fun getRuns(): Flow<List<Run>>

    suspend fun upsertRun(run: Run): Result<RunId, DataError.Local>

    suspend fun upsertRuns(runs: List<Run>): Result<List<RunId>, DataError.Local>

    suspend fun deleteRun(id: String)
    suspend fun deleteAllRuns()

}