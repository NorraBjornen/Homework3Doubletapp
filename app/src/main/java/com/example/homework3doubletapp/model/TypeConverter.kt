package com.example.homework3doubletapp.model

import androidx.room.TypeConverter

class TypeConverter {
    @TypeConverter
    fun typeToInt(type: HabitType): Int {
        return type.value
    }

    @TypeConverter
    fun intToType(int: Int): HabitType {
        return HabitType.valueOf(int)
    }
}