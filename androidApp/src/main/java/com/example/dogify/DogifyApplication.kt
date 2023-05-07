package com.example.dogify

import android.app.Application
import com.example.dogify.di.initKoin
import com.example.dogify.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class DogifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            // Log Koin into Android logger
            androidLogger()
            androidContext(this@DogifyApplication)
            modules(viewModelModules)
        }
    }
}