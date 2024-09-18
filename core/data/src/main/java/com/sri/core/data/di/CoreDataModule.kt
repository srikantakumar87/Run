package com.sri.core.data.di

import android.content.SharedPreferences
//import com.sri.core.data.auth.EncryptedSessionStorage
import com.sri.core.data.networking.HttpClientFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}