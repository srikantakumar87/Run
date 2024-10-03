package com.sri.core.data.di

import com.sri.core.data.auth.EncryptedSessionStorage
import com.sri.core.data.networking.HttpClientFactory
import com.sri.core.domain.runs.RunRepository
import com.sri.core.domain.SessionStorage
import com.sri.core.data.runs.OfflineFirstRunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}