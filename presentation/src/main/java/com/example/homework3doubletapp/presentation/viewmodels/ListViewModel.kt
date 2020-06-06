package com.example.homework3doubletapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitType
import com.example.domain.entities.ToastContentType
import com.example.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val loadHabitsUseCase: LoadHabitsUseCase,
    private val doneHabitUseCase: DoneHabitUseCase,
    private val toastContentUseCase: ToastContentUseCase
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

    fun doneHabit(habit: Habit): ToastContentType {
        viewModelScope.launch(Dispatchers.IO) {
            doneHabitUseCase.doneHabit(habit)
        }
        return toastContentUseCase.getToastContentType(habit.getRestTimes(), habit.type)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadHabitsUseCase.loadHabits()
        }
    }
}