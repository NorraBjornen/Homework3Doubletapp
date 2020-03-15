package com.example.homework3doubletapp.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.Repository
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity(), View.OnClickListener{

    var habit : Habit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        save.setOnClickListener(this)



        val k : Float = 360f / 17

        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        lp.width = resources.getDimension(R.dimen.square_size).toInt()
        lp.height = resources.getDimension(R.dimen.square_size).toInt()
        lp.marginEnd = resources.getDimension(R.dimen.margin_end).toInt()

        for(i in 0..16){
            val color = Color.HSVToColor(floatArrayOf(i*k, 100f, 100f))
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.square)
            imageView.layoutParams = lp
            imageView.setColorFilter(color)
            linear_layout.addView(imageView)
        }

        setValues()
    }

    private fun setValues(){
        habit = Repository.getHabit(intent.getStringExtra("habit"))

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

    override fun onClick(v: View?) {
        if(!bad_radio.isChecked && !good_radio.isChecked){
            Toast
                .makeText(this, "Select habit type!", Toast.LENGTH_SHORT)
                .show()
            return
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
}