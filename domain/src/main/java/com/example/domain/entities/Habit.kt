package com.example.domain.entities

import java.io.Serializable
import java.util.*

data class Habit(
    var count: Int,
    var color: Int,
    var date: Int,
    var description: String?,
    var done_dates: List<Int>?,
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
            done_dates,
            frequency,
            priority,
            title,
            type
        )
    }

    fun getRestTimes(): Int {
        val now = (Date().time / 1000).toInt()
        val threshold = now - day * frequency
        val times = count - done_dates!!.filter { it >=  threshold}.size
        return times.coerceAtLeast(0)
    }

    companion object {
        fun create(): Habit {
            return Habit(
                -1,
                -1,
                -1,
                null,
                listOf(),
                -1,
                -1,
                null,
                HabitType.UNDEFINED.value,
                "foo"
            )
        }

        const val day = 60 * 60 * 24
    }
}