package com.example.homework3doubletapp.presentation.recycler

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.R
import com.example.domain.entities.Habit
import com.example.domain.entities.HabitType
import com.example.homework3doubletapp.presentation.screens.DetailsFragment
import com.example.homework3doubletapp.presentation.viewmodels.ListViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder(override val containerView: View, private val viewModel: ListViewModel) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    lateinit var habit: Habit

    init {
        val context = containerView.context
        containerView.habit_done.setOnClickListener {
            val rest = habit.getRestTimes()
            val text = if(habit.type == HabitType.GOOD.value) {
                when(rest) {
                    0 -> context.resources.getString(R.string.good_exceed)
                    1 -> context.resources.getString(R.string.good_enough)
                    else -> context.resources.getString(R.string.good_not_exceed, rest - 1)
                }
            } else {
                when(rest) {
                    0 -> context.resources.getString(R.string.bad_exceed)
                    1 -> context.resources.getString(R.string.bad_enough)
                    else -> context.resources.getString(R.string.bad_not_exceed, rest - 1)
                }
            }
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            viewModel.doneHabit(habit)
        }
    }

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

        containerView.quantity.text = habit.getRestTimes().toString()//habit.count.toString()

        containerView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(DetailsFragment.ARGS_HABIT, habit)
            Navigation.findNavController(containerView.context as Activity, R.id.nav_host_fragment)
                .navigate(R.id.detailsFragment, bundle)
        }
    }
}