package com.example.homework3doubletapp.model

import androidx.lifecycle.LiveData
import com.example.homework3doubletapp.App
import com.example.homework3doubletapp.Subscriber

class Repository(private val habitDao: HabitDao) {

    private val subscribers = mutableListOf<Subscriber>()

    fun getHabitsLiveData(): LiveData<List<Habit>> {
        return habitDao.getHabits()
    }

    fun getHabit(id: Int?): Habit {
        if (id == null || id == -1)
            return Habit.create()

        return habitDao.getHabit(id)
    }

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

        subscribers.forEach { it() }
    }

    fun subscribe(subscriber: Subscriber) = subscribers.add(subscriber)

    fun unsubscribe(subscriber: Subscriber) = subscribers.remove(subscriber)

    companion object {
        fun get(): Repository {
            return App.repository
        }
    }
}