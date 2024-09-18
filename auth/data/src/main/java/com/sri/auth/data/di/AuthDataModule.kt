package com.sri.auth.data.di

import com.sri.auth.data.EmailPatternValidator
import com.sri.auth.domain.PatternValidator
import com.sri.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator


    }
    singleOf(::UserDataValidator)
}