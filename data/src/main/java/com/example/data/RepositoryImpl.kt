package com.example.data

import com.example.domain.entities.Habit
import com.example.domain.entities.HabitUID
import com.example.domain.Repository
import com.example.domain.entities.HabitDone
import com.example.domain.entities.SimpleHabit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val api: HabitApi, private val dao: HabitDao): Repository {
    override suspend fun saveHabitWeb(habit: SimpleHabit): HabitUID =
        api.addHabit(habit)

    override suspend fun updateHabitWeb(habit: Habit): HabitUID =
        api.updateHabit(habit)

    override suspend fun saveHabitDB(habit: Habit) =
        dao.addHabit(DataHabit(habit))

    override suspend fun saveHabitsDB(habits: List<Habit>) =
        dao.addHabits(habits.map { DataHabit(it) })

    override suspend fun updateHabitDB(habit: Habit) =
        dao.updateHabit(DataHabit(habit))

    override suspend fun getHabitsWeb(): List<Habit> =
        api.getHabits()

    override fun getHabitsDB(): Flow<List<Habit>> =
        dao.getHabits().map { list -> list.map { it.asDomainHabit() } }

    override suspend fun deleteHabitWeb(habit: Habit) =
        api.deleteHabit(HabitUID(habit.uid))

    override suspend fun deleteHabitDB(habit: Habit) =
        dao.deleteHabit(DataHabit(habit))

    override suspend fun doneHabitWeb(habitDone: HabitDone) {
        api.doneHabit(habitDone)
    }
}