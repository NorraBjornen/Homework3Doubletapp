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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val arrayList = listOf(1, 2, 3, 4, 5)

        val arrayAdapter: ArrayAdapter<Int> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        priority_spinner.adapter = arrayAdapter
        priority_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected: Int = parent.getItemAtPosition(position).toString().toInt()
                Toast
                    .makeText(parent.context, selected.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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

            val habit = Habit(
                name_edit.text.toString(),
                description_edit.text.toString(),
                priority_spinner.selectedItem.toString().toInt(),
                type,
                period_edit.text.toString().toInt(),
                Color.BLUE
            )

            Repository.addHabit(habit)

            finish()
        }
    }
}