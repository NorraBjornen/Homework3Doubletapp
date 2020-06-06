package com.example.homework3doubletapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitType
import com.example.domain.usecases.SaveHabitUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class DetailsViewModel @Inject constructor(private val save: SaveHabitUseCase) : ViewModel() {

    val done = MutableLiveData<Boolean>()

    fun resolveHabit(
        habit: Habit,
        name: String?,
        description: String?,
        priority: Int,
        type: HabitType,
        period: Int,
        quantity: Int,
        color: Int
    ) {

        val isNewHabit = habit.type == HabitType.UNDEFINED.value

        habit.title = name
        habit.description = description
        habit.priority = priority
        habit.type = type.value
        habit.frequency = quantity
        habit.count = period
        habit.color = color
        habit.date = (Date().time / 1000).toInt()

        viewModelScope.launch(Dispatchers.IO) {
            save.saveHabit(habit, isNewHabit)
            done.postValue(true)
        }
    }
}