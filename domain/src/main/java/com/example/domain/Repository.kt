package com.example.domain

import com.example.domain.entities.Habit
import com.example.domain.entities.HabitDone
import com.example.domain.entities.HabitUID
import com.example.domain.entities.SimpleHabit
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveHabitWeb(habit: SimpleHabit): HabitUID
    suspend fun updateHabitWeb(habit: Habit): HabitUID

    suspend fun saveHabitDB(habit: Habit)
    suspend fun saveHabitsDB(habits: List<Habit>)
    suspend fun updateHabitDB(habit: Habit)

    fun getHabitsDB(): Flow<List<Habit>>
    suspend fun getHabitsWeb(): List<Habit>

    suspend fun deleteHabitDB(habit: Habit)
    suspend fun deleteHabitWeb(habit: Habit)

    suspend fun doneHabitWeb(habitDone: HabitDone)
}