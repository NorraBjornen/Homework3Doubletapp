package com.example.homework3doubletapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.data.RepositoryImpl
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.entities.Habit
import com.example.domain.usecases.LoadHabitsUseCase
import com.example.homework3doubletapp.presentation.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val mutableStraightOrder = MutableLiveData<Boolean>()
    val straightOrder: LiveData<Boolean> = mutableStraightOrder

    private val mutableFilterString = MutableLiveData<String>()
    val filterString: LiveData<String> = mutableFilterString

    fun filter(string: String?) {
        mutableFilterString.value = string
    }

    fun changeOrder(straight: Boolean?) {
        mutableStraightOrder.value = straight
    }

    @ExperimentalCoroutinesApi
    fun getHabits(): LiveData<List<Habit>> {
        return GetHabitsUseCase(
            RepositoryImpl(
                App.api,
                App.dao
            ), Dispatchers.IO
        ).getHabits().asLiveData()
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            DeleteHabitUseCase(
                RepositoryImpl(
                    App.api,
                    App.dao
                ), Dispatchers.IO
            ).deleteHabit(habit)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            LoadHabitsUseCase(
                RepositoryImpl(
                    App.api,
                    App.dao
                ), Dispatchers.IO
            ).loadHabits()
        }
    }
}