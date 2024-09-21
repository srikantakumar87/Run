package com.sri.auth.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.sri.auth.presentation.register.RegisterViewModel
import com.sri.auth.presentation.login.LoginViewModel
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)




}