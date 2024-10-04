package com.sri.analytics.data.di

import com.sri.analytics.data.RoomAnalyticsRepository
import com.sri.analytics.domain.AnalyticsRepository
import com.sri.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single{
        get<RunDatabase>().analyticsDao

    }
}