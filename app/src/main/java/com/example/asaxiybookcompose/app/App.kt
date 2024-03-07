package com.example.asaxiybookcompose.app

import android.app.Application
import com.example.asaxiybookcompose.data.source.MySharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
    }

}