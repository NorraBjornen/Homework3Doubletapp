package com.example.domain.usecases

import com.example.domain.entities.Habit
import com.example.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetHabitsUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    fun getHabits(): Flow<List<Habit>> = repository.getHabits().flowOn(dispatcher)
}