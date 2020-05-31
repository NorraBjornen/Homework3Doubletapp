package com.example.data

import androidx.room.TypeConverter
import com.example.domain.entities.HabitType

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