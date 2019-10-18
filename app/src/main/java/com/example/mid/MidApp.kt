package com.example.mid

import android.app.Application
import dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import repoModule
import viewModelModule

class MidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(dbModule, repoModule, viewModelModule))
        }
    }
}