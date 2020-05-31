package com.example.homework3doubletapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.entities.Habit
import com.example.domain.usecases.LoadHabitsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class ListViewModel(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val loadHabitsUseCase: LoadHabitsUseCase
) : ViewModel() {

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
        return getHabitsUseCase.getHabits().asLiveData()
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteHabitUseCase.deleteHabit(habit)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadHabitsUseCase.loadHabits()
        }
    }
}