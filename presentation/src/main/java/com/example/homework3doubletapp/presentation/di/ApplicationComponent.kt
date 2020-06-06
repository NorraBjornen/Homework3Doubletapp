package com.example.homework3doubletapp.presentation.di

import com.example.homework3doubletapp.presentation.BottomSheetFragment
import com.example.homework3doubletapp.presentation.di.modules.ContextModule
import com.example.homework3doubletapp.presentation.di.modules.HabitsModule
import com.example.homework3doubletapp.presentation.di.modules.ViewModelModule
import com.example.homework3doubletapp.presentation.screens.DetailsFragment
import com.example.homework3doubletapp.presentation.screens.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HabitsModule::class, ContextModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: BottomSheetFragment)
}