package com.example.homework3doubletapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }

        val habits = mutableListOf(Habit("Name", "Description", 1, HabitType.GOOD, 1, Color.BLACK))

        recycler.adapter = Adapter(habits)
    }
}
