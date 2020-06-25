package com.example.homework3doubletapp.presentation

import com.example.domain.Repository
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitType
import com.example.domain.entities.HabitUID
import com.example.domain.entities.ToastContentType
import com.example.domain.usecases.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class InteractorTest {

    private val repo: Repository = mock()
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        runBlockingTest {
            whenever(repo.getHabitsDB()).thenReturn(
                flowOf(
                    listOf(
                        Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                        Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
                    )
                )
            )

            whenever(repo.saveHabitWeb(any())).thenReturn(
                HabitUID("uid")
            )

            whenever(repo.updateHabitWeb(any())).thenReturn(
                HabitUID("uid")
            )
        }
    }

    @Test
    fun deleteHabitUseCaseTest() = runBlockingTest {
        val d = DeleteHabitUseCase(repo, testDispatcher)

        val res = d.deleteHabit(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4")
        )

        assertEquals(res, Unit)
    }

    @Test
    fun doneHabitUseCaseTest() = runBlockingTest {
        val d = DoneHabitUseCase(repo, testDispatcher)

        val res = d.doneHabit(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4")
        )

        assertEquals(res, Unit)
    }

    @Test
    fun getHabitsUseCaseTest() = runBlockingTest {
        val d = GetHabitsUseCase(repo, testDispatcher)

        val res = d.getHabits()

        assertEquals(
            res.first(), listOf(
                Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
            )
        )
    }

    @Test
    fun loadHabitsUseCaseTest() = runBlockingTest {
        val d = LoadHabitsUseCase(repo, testDispatcher)

        val res = d.loadHabits()

        assertEquals(
            res, Unit
        )
    }

    @Test
    fun saveHabitUseCaseTest() = runBlockingTest {
        val d = SaveHabitUseCase(repo, testDispatcher)

        val res1 = d.saveHabit(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
            true
        )

        val res2 = d.saveHabit(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
            false
        )

        assertEquals(
            res1, Unit
        )
        assertEquals(
            res2, Unit
        )
    }

    @Test
    fun toastContentUseCaseTest() = runBlockingTest {
        val d = ToastContentUseCase()

        assertEquals(ToastContentType.GOOD_EXCEED, d.getToastContentType(0, HabitType.GOOD.value))
        assertEquals(ToastContentType.GOOD_ENOUGH, d.getToastContentType(1, HabitType.GOOD.value))
        assertEquals(ToastContentType.GOOD_NOT_EXCEED, d.getToastContentType(2, HabitType.GOOD.value))

        assertEquals(ToastContentType.BAD_EXCEED, d.getToastContentType(0, HabitType.BAD.value))
        assertEquals(ToastContentType.BAD_ENOUGH, d.getToastContentType(1, HabitType.BAD.value))
        assertEquals(ToastContentType.BAD_NOT_EXCEED, d.getToastContentType(2, HabitType.BAD.value))
    }
}