package com.example.domain

import com.example.domain.entities.Habit
import com.example.domain.entities.HabitUID
import com.example.domain.entities.SimpleHabit
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveHabitWeb(habit: SimpleHabit): HabitUID
    suspend fun updateHabitWeb(habit: Habit): HabitUID

    suspend fun saveHabitDB(habit: Habit)
    suspend fun updateHabitDB(habit: Habit)

    fun getHabits(): Flow<List<Habit>>

    suspend fun loadHabits()

    suspend fun deleteHabit(habit: Habit)
}