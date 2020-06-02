package com.example.data

import androidx.room.TypeConverter
import com.example.domain.entities.HabitType
import java.text.SimpleDateFormat
import java.util.*

class TypeConverter {
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale("ru"))

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

    @TypeConverter
    fun listDatesToString(dates: List<Int>) : String = dates.joinToString(";")

    @TypeConverter
    fun stringToListDates(string: String) : List<Int> =
        if(string == "")
            listOf()
        else
            string.split(";").map{it.toInt()}
}