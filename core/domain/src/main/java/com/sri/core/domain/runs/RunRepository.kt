package com.sri.core.domain.runs

import com.sri.core.domain.runs.Run
import com.sri.core.domain.runs.RunId
import com.sri.core.domain.util.DataError
import com.sri.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface RunRepository {
    fun getRuns(): Flow<List<Run>>
    suspend fun fetchRuns(): EmptyResult<DataError>
    suspend fun upsertRun(run: Run, mapPicture: ByteArray): EmptyResult<DataError>
    suspend fun deleteRun(id: RunId)
    suspend fun syncPendingRuns()
    suspend fun logout(): EmptyResult<DataError.Network>
    suspend fun deleteAllRuns()




}