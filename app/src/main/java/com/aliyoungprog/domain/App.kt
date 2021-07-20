package com.aliyoungprog.domain

import android.app.Application
import androidx.databinding.library.BuildConfig

import com.aliyoungprog.domain.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(koinModules)
        }
        Timber.plant(Timber.DebugTree())
    }
}
