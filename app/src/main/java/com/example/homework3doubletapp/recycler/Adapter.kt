package com.example.homework3doubletapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit

class Adapter(private val habits: List<Habit>) : RecyclerView.Adapter<ViewHolder>() {
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
    override fun getItemCount(): Int = habits.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])
    }
}