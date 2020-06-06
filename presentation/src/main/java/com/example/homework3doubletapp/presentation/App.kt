package com.example.homework3doubletapp.presentation

import android.app.Application
import com.example.homework3doubletapp.presentation.di.ApplicationComponent
import com.example.homework3doubletapp.presentation.di.DaggerApplicationComponent
import com.example.homework3doubletapp.presentation.di.modules.ContextModule

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent.builder().contextModule(
                ContextModule(
                    this
                )
            ).build()
    }
}