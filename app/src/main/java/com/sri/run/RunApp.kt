package com.sri.run

import android.app.Application
import com.sri.auth.data.di.authDataModule
import com.sri.auth.presentation.di.authViewModelModule
import com.sri.core.data.di.coreDataModule
import com.sri.run.di.appModule
import com.sri.runs.presentation.di.runViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class RunApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())

        }
        startKoin {
            androidLogger()
            androidContext(this@RunApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runViewModelModule,






            )


        }
    }
}