package com.example.homework3doubletapp.model

import java.io.Serializable

class Habit (var name: String,
             var description: String,
             var priority: Int,
             var type: HabitType,
             var period: Int,
             var quantity : Int,
             var color: Int)