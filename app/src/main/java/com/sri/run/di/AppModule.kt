package com.sri.run.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sri.auth.data.EmailPatternValidator
import com.sri.auth.domain.PatternValidator
import com.sri.auth.domain.UserDataValidator
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.sri.run.MainViewModel

val appModule = module {

    single<SharedPreferences> {
       EncryptedSharedPreferences(
           androidApplication(),
           "auth_pref",
           MasterKey(androidApplication()),
           EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
           EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM

       )
    }

    viewModelOf(::MainViewModel)

}
