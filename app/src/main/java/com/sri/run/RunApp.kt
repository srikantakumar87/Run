package com.sri.run

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.sri.auth.data.di.authDataModule
import com.sri.auth.presentation.di.authViewModelModule
import com.sri.core.data.di.coreDataModule
import com.sri.core.database.di.databaseModule
import com.sri.run.di.appModule
import com.sri.runs.location.di.locationModule
import com.sri.runs.network.di.networkModule
import com.sri.runs.presentation.di.RunPresentationModule
import com.sri.runs.data.di.runDataModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
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
            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                RunPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule

            )


        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)

    }
}