package com.example.homework3doubletapp.model.database

import androidx.room.TypeConverter
import com.example.homework3doubletapp.model.HabitType

class TypeConverter {
    @TypeConverter
    fun typeToInt(type: HabitType): Int {
        return type.value
    }

    @TypeConverter
    fun intToType(int: Int): HabitType {
        return HabitType.valueOf(
            int
        )
    }
}