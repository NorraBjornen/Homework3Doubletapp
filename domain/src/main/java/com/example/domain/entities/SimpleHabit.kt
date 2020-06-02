package com.example.domain.entities

data class SimpleHabit(
    var count: Int,
    var color: Int,
    var date: Int,
    var description: String?,
    var done_dates: List<Int>?,
    var frequency: Int,
    var priority: Int,
    var title: String?,
    var type: Int
)