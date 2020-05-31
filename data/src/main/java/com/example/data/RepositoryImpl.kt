package com.example.data

import com.example.domain.entities.Habit
import com.example.domain.entities.HabitUID
import com.example.domain.Repository
import com.example.domain.entities.SimpleHabit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val api: HabitApi, private val dao: HabitDao): Repository {
    override suspend fun saveHabitWeb(habit: SimpleHabit): HabitUID = api.addHabit(habit)

    override suspend fun updateHabitWeb(habit: Habit): HabitUID = api.updateHabit(habit)

    override suspend fun saveHabitDB(habit: Habit) {
        dao.addHabit(Habit(habit))
    }

    override suspend fun updateHabitDB(habit: Habit) {
        dao.updateHabit(Habit(habit))
    }

    override fun getHabits(): Flow<List<Habit>> = dao.getHabits().map { list -> list.map { it.asDomainHabit() } }

    override suspend fun loadHabits() {
        val habits = api.getHabits()
        dao.addHabits(habits.map { Habit(it) })
    }

    override suspend fun deleteHabit(habit: Habit) {
        api.deleteHabit(HabitUID(habit.uid))
        dao.deleteHabit(Habit(habit))
    }
}