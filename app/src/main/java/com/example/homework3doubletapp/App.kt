package com.example.homework3doubletapp

import android.app.Application
import androidx.room.Room
import com.example.homework3doubletapp.model.Database
import com.example.homework3doubletapp.model.HabitDao
import com.example.homework3doubletapp.model.Repository

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            this,
            Database::class.java, "db"
        ).fallbackToDestructiveMigration().build()

        val habitDao = db.habitDao()

        repository = Repository(habitDao)
    }

    companion object {
        lateinit var repository: Repository
    }
}