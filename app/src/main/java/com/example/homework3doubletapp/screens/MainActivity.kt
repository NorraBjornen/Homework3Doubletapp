package com.example.homework3doubletapp.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.Repository
import com.example.homework3doubletapp.recycler.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter : Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }

        val decoration =
            DividerItemDecoration(applicationContext, VERTICAL)
        recycler.addItemDecoration(decoration)

        adapter = Adapter(Repository.habits)


        recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
}
