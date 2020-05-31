package com.example.homework3doubletapp.presentation

import com.example.homework3doubletapp.presentation.screens.DetailsFragment
import com.example.homework3doubletapp.presentation.screens.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HabitsModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: BottomSheetFragment)
}