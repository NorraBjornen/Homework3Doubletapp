package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DeleteHabitUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun deleteHabit(habit: Habit) {
        withContext(dispatcher) {
            repository.deleteHabit(habit)
        }
    }
}