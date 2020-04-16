package com.example.homework3doubletapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homework3doubletapp.model.Habit

@Database(entities = [Habit::class], version = 4)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase(){
    abstract fun habitDao(): HabitDao
}