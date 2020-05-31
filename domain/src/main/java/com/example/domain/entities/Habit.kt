package com.example.domain.entities

import java.io.Serializable

data class Habit(
    var count: Int,
    var color: Int,
    var date: Int,
    var description: String?,
    var frequency: Int,
    var priority: Int,
    var title: String?,
    var type: Int,
    var uid: String

) : Serializable {

    fun addUid(uid: HabitUID) {
        this.uid = uid.uid
    }

    fun toSimpleHabit(): SimpleHabit {
        return SimpleHabit(
            count,
            color,
            date,
            description,
            frequency,
            priority,
            title,
            type
        )
    }

    companion object {
        fun create(): Habit {
            return Habit(
                -1,
                -1,
                -1,
                null,
                -1,
                -1,
                null,
                HabitType.UNDEFINED.value,
                "foo"
            )
        }
    }
}