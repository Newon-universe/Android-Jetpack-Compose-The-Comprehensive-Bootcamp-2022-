package com.example.giphy

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GiphyApplication : Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: GiphyApplication
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }
}