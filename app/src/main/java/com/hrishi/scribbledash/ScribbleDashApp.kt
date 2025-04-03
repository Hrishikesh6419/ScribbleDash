package com.hrishi.scribbledash

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.hrishi.scribbledash.di.appModule
import com.hrishi.scribbledash.home.presentation.di.homePresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ScribbleDashApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@ScribbleDashApp)
            modules(
                appModule,
                homePresentationModule
            )
        }
    }
}