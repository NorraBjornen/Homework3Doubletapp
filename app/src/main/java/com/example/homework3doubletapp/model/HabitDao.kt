package com.example.homework3doubletapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert
    suspend fun addHabit(habit: Habit)

    @Query("select * from habit")
    fun getHabits(): LiveData<List<Habit>>

    @Update
    suspend fun updateHabit(habit: Habit)
}