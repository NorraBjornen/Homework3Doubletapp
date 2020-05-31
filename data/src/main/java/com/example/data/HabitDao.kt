package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert
    suspend fun addHabit(habit: DataHabit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabits(habits: List<DataHabit>)

    @Query("select * from datahabit")
    fun getHabits(): Flow<List<DataHabit>>

    @Update
    suspend fun updateHabit(habit: DataHabit)

    @Delete
    suspend fun deleteHabit(habit: DataHabit)
}