package com.example.homework3doubletapp.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.screen_controller.FragmentChanger
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(habit: Habit) {
        containerView.name.text = habit.name
        containerView.description.text = habit.description

        val resId = when (habit.type) {
            HabitType.GOOD -> R.drawable.like
            HabitType.BAD -> R.drawable.dislike
        }

        containerView.type.setImageResource(resId)
        containerView.type.setColorFilter(habit.color)

        containerView.period.text = habit.period.toString()

        containerView.priority.text = habit.priority.toString()

        containerView.quantity.text = habit.quantity.toString()

        containerView.setOnClickListener {
            (containerView.context as FragmentChanger).startDetailsScreen(habit.name)
            /*val intent = Intent(containerView.context, DetailsActivity::class.java)
            intent.putExtra("habit", habit.name)
            containerView.context.startActivity(intent)*/
        }
    }
}