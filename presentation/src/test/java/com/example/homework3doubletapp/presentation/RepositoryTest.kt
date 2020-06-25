package com.example.homework3doubletapp.presentation

import com.example.data.DataHabit
import com.example.data.HabitApi
import com.example.data.HabitDao
import com.example.data.RepositoryImpl
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitDone
import com.example.domain.entities.HabitUID
import com.example.domain.entities.SimpleHabit
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RepositoryTest {

    private val apiMock: HabitApi = mock()
    private val daoMock: HabitDao = mock()
    private val repo = RepositoryImpl(apiMock, daoMock)

    @Before
    fun setup() {
        runBlocking {
            whenever(
                apiMock.addHabit(
                    any()
                )
            ).thenReturn(
                HabitUID("uid1")
            )

            whenever(
                apiMock.updateHabit(
                    any()
                )
            ).thenReturn(
                HabitUID("uid2")
            )

            whenever(
                apiMock.getHabits()
            ).thenReturn(
                listOf(
                    Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                    Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
                )
            )

            whenever(
                daoMock.getHabits()
            ).thenReturn(
                flowOf(
                    listOf(
                        DataHabit(
                            1,
                            1,
                            1,
                            "desc1",
                            listOf(),
                            1,
                            1,
                            "title1",
                            0,
                            "uid4"
                        ),
                        DataHabit(
                            1,
                            1,
                            1,
                            "desc2",
                            listOf(),
                            1,
                            1,
                            "title2",
                            0,
                            "uid5"
                        )
                    )
                )
            )
        }
    }

    @Test
    fun saveHabitWebTest(): Unit = runBlocking {
        val uid = repo.saveHabitWeb(
            SimpleHabit(1, 1, 1, "desc", listOf(), 1, 1, "title", 0)
        )

        assertEquals(
            uid,
            HabitUID("uid1")
        )
    }

    @Test
    fun updateHabitWebTest(): Unit = runBlocking {
        val uid = repo.updateHabitWeb(
            Habit(1, 1, 1, "desc", listOf(), 1, 1, "title", 0, "uid2")
        )

        assertEquals(
            uid,
            HabitUID("uid2")
        )
    }

    @Test
    fun saveHabitDBTest(): Unit = runBlocking {
        val uid = repo.saveHabitDB(
            Habit(1, 1, 1, "desc", listOf(), 1, 1, "title", 0, "uid3")
        )

        assertEquals(
            uid,
            Unit
        )
    }

    @Test
    fun saveHabitsDBTest(): Unit = runBlocking {
        val uid = repo.saveHabitsDB(
            listOf(
                Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
            )
        )

        assertEquals(
            uid,
            Unit
        )
    }

    @Test
    fun updateHabitDBTest(): Unit = runBlocking {
        val uid = repo.updateHabitDB(
            Habit(1, 1, 1, "desc", listOf(), 1, 1, "title", 0, "uid6")
        )

        assertEquals(
            uid,
            Unit
        )
    }

    @Test
    fun getHabitsWebTest(): Unit = runBlocking {
        val res = repo.getHabitsWeb()

        assertEquals(
            res,
            listOf(
                Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
            )
        )
    }

    @Test
    fun getHabitsDBTest(): Unit = runBlocking {
        val res = repo.getHabitsDB().first()

        assertEquals(
            res,
            listOf(
                Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4"),
                Habit(1, 1, 1, "desc2", listOf(), 1, 1, "title2", 0, "uid5")
            )
        )
    }

    @Test
    fun deleteHabitWebTest(): Unit = runBlocking {
        val res = repo.deleteHabitWeb(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4")
        )

        assertEquals(
            res,
            Unit
        )
    }

    @Test
    fun deleteHabitDBTest(): Unit = runBlocking {
        val res = repo.deleteHabitDB(
            Habit(1, 1, 1, "desc1", listOf(), 1, 1, "title1", 0, "uid4")
        )

        assertEquals(
            res,
            Unit
        )
    }

    @Test
    fun doneHabitWebTest(): Unit = runBlocking {
        val res = repo.doneHabitWeb(
            HabitDone(0, "uid")
        )

        assertEquals(
            res,
            Unit
        )
    }
}