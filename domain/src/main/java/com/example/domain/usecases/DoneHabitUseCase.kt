package com.example.domain.usecases

import com.example.domain.Repository
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitDone
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import sun.rmi.runtime.Log
import java.util.*

class DoneHabitUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun doneHabit(habit: Habit) {
        withContext(dispatcher) {
            val date: Int = (Date().time / 1000).toInt()

            val col = mutableListOf<Int>()
            col.addAll(habit.done_dates!!)
            col.add(date)
            habit.done_dates = col

            repository.doneHabitWeb(HabitDone(date, habit.uid))
            repository.updateHabitDB(habit)
        }
    }
}