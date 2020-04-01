package com.example.homework3doubletapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit

class Adapter : RecyclerView.Adapter<ViewHolder>() {

    private var filterString = ""
    private val straightOrder = true

    private var myHabits: List<Habit> = listOf()
    private var currentItems: List<Habit> = myHabits

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
        return currentItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentItems[position])
    }

    fun setItems(list: List<Habit>){
        myHabits = list
        actualizeItems(filterString, straightOrder)
    }

    fun actualizeItems(prefix: String, order: Boolean) {
        currentItems = myHabits.filter { it.name?.startsWith(prefix) ?: false }

        currentItems =
            if (order)
                currentItems.sortedBy { it.priority }
            else
                currentItems.sortedByDescending { it.priority }

        notifyDataSetChanged()
    }
}
