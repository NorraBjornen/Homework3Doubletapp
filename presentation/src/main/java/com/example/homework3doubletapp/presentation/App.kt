package com.example.homework3doubletapp.presentation

import android.app.Application

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
    }
}