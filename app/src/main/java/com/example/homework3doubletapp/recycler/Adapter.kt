package com.example.homework3doubletapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.Repository

class Adapter(private val habits: List<Habit>, private val habitType: HabitType) : RecyclerView.Adapter<ViewHolder>() {

    private var myHabits : List<Habit> = Repository.habits.filter { it.type == habitType }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return myHabits.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myHabits[position])
    }

    fun setItems(){
        myHabits = Repository.habits.filter {
            it.type == habitType
        }
    }
}
