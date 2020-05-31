package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SaveHabitUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun saveHabit(habit: Habit, isNewHabit: Boolean) {
        withContext(dispatcher) {
            val uid = if (isNewHabit) {
                repository.saveHabitWeb(habit.toSimpleHabit())
            } else {
                repository.updateHabitWeb(habit)
            }

            habit.addUid(uid)

            if (isNewHabit) {
                repository.saveHabitDB(habit)
            } else {
                repository.updateHabitDB(habit)
            }
        }
    }
}