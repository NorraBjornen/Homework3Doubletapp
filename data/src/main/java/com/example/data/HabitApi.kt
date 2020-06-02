package com.example.data

import com.example.domain.entities.HabitUID
import com.example.domain.entities.SimpleHabit
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitDone
import retrofit2.http.*

interface HabitApi {
    @GET("habit")
    suspend fun getHabits(): List<Habit>

    @PUT("habit")
    suspend fun updateHabit(@Body habit: Habit): HabitUID

    @PUT("habit")
    suspend fun addHabit(@Body habit: SimpleHabit): HabitUID

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Body uid: HabitUID)

    @HTTP(method = "POST", path = "habit_done", hasBody = true)
    suspend fun doneHabit(@Body habitDone: HabitDone)
}