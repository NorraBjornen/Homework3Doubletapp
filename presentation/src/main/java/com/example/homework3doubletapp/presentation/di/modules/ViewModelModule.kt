package com.example.homework3doubletapp.presentation.di.modules

import androidx.lifecycle.ViewModel
import com.example.homework3doubletapp.presentation.di.ViewModelKey
import com.example.homework3doubletapp.presentation.viewmodels.DetailsViewModel
import com.example.homework3doubletapp.presentation.viewmodels.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun detailsViewModel(detailsViewModel: DetailsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun listViewModel(listViewModel: ListViewModel?): ViewModel?
}