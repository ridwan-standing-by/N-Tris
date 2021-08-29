package com.ridwanstandingby.ntris

import android.app.Application
import com.ridwanstandingby.ntris.di.KoinInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NTrisAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            allowOverride(true)
            androidLogger()
            androidContext(this@NTrisAndroidApplication)
            modules(KoinInjector.buildModule())
        }
    }
}
