package com.example.homework3doubletapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.Repository
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitUID
import com.example.domain.usecases.*
import com.example.homework3doubletapp.presentation.viewmodels.ListViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repo: Repository = mock()
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private val vm = ListViewModel(
        GetHabitsUseCase(repo, testDispatcher),
        DeleteHabitUseCase(repo, testDispatcher),
        LoadHabitsUseCase(repo, testDispatcher),
        DoneHabitUseCase(repo, testDispatcher),
        ToastContentUseCase()
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        whenever(repo.getHabitsDB()).thenReturn(
            flowOf(
                listOf(
                    Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                    Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
                )
            )
        )
    }

    @Test
    fun getHabitsTest() = runBlockingTest {
        val habits = vm.getHabits().value

        assertEquals(
            habits, listOf(
                Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
            )
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}