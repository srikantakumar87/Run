package com.sri.run

import android.app.Application
import com.sri.auth.data.di.authDataModule
import com.sri.auth.presentation.di.authViewModelModule
import com.sri.core.data.di.coreDataModule
import com.sri.core.database.di.databaseModule
import com.sri.run.di.appModule
import com.sri.runs.location.di.locationModule
import com.sri.runs.presentation.di.RunPresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class RunApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

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
                RunPresentationModule,
                locationModule,
                databaseModule






            )


        }
    }
}