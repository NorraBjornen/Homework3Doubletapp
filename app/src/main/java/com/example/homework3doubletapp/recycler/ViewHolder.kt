package com.example.homework3doubletapp.recycler

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Habit
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.Repository
import com.example.homework3doubletapp.screens.DetailsFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    lateinit var habit: Habit

    fun bind(habit: Habit) {
        this.habit = habit

        containerView.name.text = habit.title
        containerView.description.text = habit.description

        val resId = when (habit.type) {
            HabitType.GOOD.value -> R.drawable.like
            HabitType.BAD.value -> R.drawable.dislike
            else -> throw IllegalStateException("Illegal type")
        }

        containerView.type.setImageResource(resId)
        containerView.type.setColorFilter(habit.color)

        containerView.period.text = habit.frequency.toString()

        containerView.priority.text = habit.priority.toString()

        containerView.quantity.text = habit.count.toString()

        containerView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(DetailsFragment.ARGS_HABIT, habit)
            Navigation.findNavController(containerView.context as Activity, R.id.nav_host_fragment)
                .navigate(R.id.detailsFragment, bundle)
        }
    }
}