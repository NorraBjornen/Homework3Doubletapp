package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DataHabit::class], version = 5)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase(){
    abstract fun habitDao(): HabitDao
}