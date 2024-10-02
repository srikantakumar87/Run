package com.sri.runs.data.di

//import com.sri.core.domain.runs.SyncRunScheduler
import com.sri.runs.data.CreateRunWorker
import com.sri.runs.data.DeleteRunWorker
import com.sri.runs.data.FetchRunsWorker
//import com.sri.runs.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::FetchRunsWorker)
    workerOf(::CreateRunWorker)
    workerOf(::DeleteRunWorker)

    //singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}