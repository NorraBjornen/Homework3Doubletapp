package com.example.homework3doubletapp.model

import androidx.lifecycle.ViewModel

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