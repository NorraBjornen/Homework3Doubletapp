package com.example.homework3doubletapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            Repository.get().resolveHabit(
                habit,
                name,
                description,
                priority,
                type,
                period,
                quantity,
                color
            )
        }
    }
}