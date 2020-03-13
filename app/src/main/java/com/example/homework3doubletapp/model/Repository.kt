package com.example.homework3doubletapp.model

import android.graphics.Color

class Repository {
    companion object {
        val habits = mutableListOf<Habit>(
            Habit(
                "Зарядка",
                "Делать зарядку",
                1,
                HabitType.GOOD,
                1,
                3,
                Color.GREEN
            ),
            Habit(
                "Курить",
                "Курить сигареты",
                2,
                HabitType.BAD,
                2,
                1,
                Color.BLACK
            )
        )

        fun addHabit(habit: Habit){
            habits.add(habit)
        }

        fun getHabit(name: String) : Habit? {
            habits.forEach {
                if(it.name == name)
                    return it
            }
            return null
        }
    }
}