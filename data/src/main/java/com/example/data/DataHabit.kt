package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.HabitType
import com.example.domain.entities.HabitUID
import com.example.domain.entities.SimpleHabit
import java.io.Serializable

@Entity
data class DataHabit(
    @ColumnInfo var count: Int,
    @ColumnInfo var color: Int,
    @ColumnInfo var date: Int,
    @ColumnInfo var description: String?,
    @ColumnInfo var frequency: Int,
    @ColumnInfo var priority: Int,
    @ColumnInfo var title: String?,
    @ColumnInfo var type: Int,
    @PrimaryKey @ColumnInfo var uid: String

) : Serializable {

    constructor(habit: com.example.domain.entities.Habit) : this(
        habit.count,
        habit.color,
        habit.date,
        habit.description,
        habit.frequency,
        habit.priority,
        habit.title,
        habit.type,
        habit.uid
    )

    fun asDomainHabit(): com.example.domain.entities.Habit {
        return com.example.domain.entities.Habit(
            count,
            color,
            date,
            description,
            frequency,
            priority,
            title,
            type,
            uid
        )
    }
}