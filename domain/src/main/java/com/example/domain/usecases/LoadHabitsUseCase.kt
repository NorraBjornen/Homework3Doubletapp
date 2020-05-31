package com.example.domain.usecases

import com.example.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoadHabitsUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun loadHabits() {
        withContext(dispatcher) {
            val habits = repository.getHabitsWeb()
            repository.saveHabitsDB(habits)
        }
    }
}