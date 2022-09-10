package com.example.appnomichallenge.ui

import android.app.Application
import com.example.appnomichallenge.ui.base.helper.UIFontSize
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        UIFontSize.init(applicationContext)
    }
}