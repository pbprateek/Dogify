package com.example.dogify

import android.app.Application
import com.example.dogify.di.initKoin

class DogifyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}