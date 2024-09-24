package com.sri.runs.location.di

import com.sri.runs.domain.LocationObserver
import com.sri.runs.location.AndroidLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}

