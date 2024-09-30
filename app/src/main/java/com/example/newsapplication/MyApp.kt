package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //koin for dependency injection.
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}