package com.example.homework3doubletapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(habit: Habit) {
        containerView.name.text = habit.name
        containerView.circle.setColorFilter(habit.color)

        val resId = when (habit.type) {
            HabitType.GOOD -> R.drawable.like
            HabitType.BAD -> R.drawable.dislike
        }

        containerView.type.setImageResource(resId)
        containerView.type.setColorFilter(habit.color)


    }
}