package com.example.homework3doubletapp.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.Repository
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    var habit : Habit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val name = intent.getStringExtra("habit")

        if(name != null)
            habit = Repository.getHabit(name)

        save.setOnClickListener {
            if(!bad_radio.isChecked && !good_radio.isChecked){
                Toast
                    .makeText(this, "Select habit type!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val type = when {
                bad_radio.isChecked -> HabitType.BAD
                good_radio.isChecked -> HabitType.GOOD
                else -> throw IllegalStateException("No type selected")
            }

            if(habit == null){
                val habit = Habit(
                    name_edit.text.toString(),
                    description_edit.text.toString(),
                    priority_spinner.selectedItem.toString().toInt(),
                    type,
                    period_edit.text.toString().toInt(),
                    quantity_edit.text.toString().toInt(),
                    Color.BLUE
                )

                Repository.addHabit(habit)
            } else {
                habit?.name = name_edit.text.toString()
                habit?.description = description_edit.text.toString()
                habit?.priority = priority_spinner.selectedItem.toString().toInt()
                habit?.type = type
                habit?.period =  period_edit.text.toString().toInt()
                habit?.quantity = quantity_edit.text.toString().toInt()
                habit?.color = Color.BLUE
            }



            finish()
        }

        if(habit != null){
            name_edit.setText(habit?.name)
            description_edit.setText(habit?.description)
            priority_spinner.setSelection(habit?.priority!! - 1)
            when (habit?.type) {
                HabitType.GOOD -> good_radio.isChecked = true
                HabitType.BAD -> bad_radio.isChecked = true
            }
            quantity_edit.setText(habit?.quantity!!.toString())
            period_edit.setText(habit?.period!!.toString())
        }
    }
}