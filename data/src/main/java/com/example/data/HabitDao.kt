package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert
    suspend fun addHabit(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabits(habits: List<Habit>)

    @Query("select * from habit")
    fun getHabits(): Flow<List<Habit>>

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)
}