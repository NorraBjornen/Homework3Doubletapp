package com.example.homework3doubletapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Adapter : RecyclerView.Adapter<ViewHolder>() {

    private var filterString = ""
    private var straightOrder = true

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
        actualizeItems()
    }

    fun actualizeItems(prefix: String) {
        filterString = prefix
        actualizeItems()
    }

    fun actualizeItems(order: Boolean) {
        straightOrder = order
        actualizeItems()
    }

    private fun actualizeItems() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                currentItems = myHabits.filter { it.name?.startsWith(filterString) ?: false }
                currentItems = withContext(Dispatchers.Default) {
                    if (straightOrder)
                        currentItems.sortedBy { it.priority }
                    else
                        currentItems.sortedByDescending { it.priority }
                }
            }
            notifyDataSetChanged()
        }
    }
}
