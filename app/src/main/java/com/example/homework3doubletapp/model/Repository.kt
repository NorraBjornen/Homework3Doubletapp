package com.example.homework3doubletapp.model

import androidx.lifecycle.LiveData
import com.example.homework3doubletapp.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(private val habitDao: HabitDao) {

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return habitDao.getHabits()
    }

    suspend fun resolveHabit(
        habit: Habit,
        name: String?,
        description: String?,
        priority: Int,
        type: HabitType,
        period: Int,
        quantity: Int,
        color: Int
    ) {
        val isNewHabit = habit.type == HabitType.UNDEFINED

        habit.name = name
        habit.description = description
        habit.priority = priority
        habit.type = type
        habit.period = period
        habit.quantity = quantity
        habit.color = color


        if (isNewHabit) {
            habitDao.addHabit(habit)
        } else {
            habitDao.updateHabit(habit)
        }
    }

    companion object {
        fun get(): Repository {
            return App.repository
        }
    }
}