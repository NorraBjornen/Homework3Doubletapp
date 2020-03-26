package com.example.homework3doubletapp.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.addDivider
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.ListViewModel
import com.example.homework3doubletapp.model.Repository
import com.example.homework3doubletapp.recycler.Adapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel() as T
            }
        }).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.addDivider(activity as Context)

        val habitType = arguments?.get(ARGS_TYPE) as HabitType

        adapter = Adapter()
        val items =
            if (habitType == HabitType.GOOD)
                viewModel.goodHabits
            else
                viewModel.badHabits

        adapter.setItems(items)
        recycler.adapter = adapter

        sheet_search.addTextChangedListener {editable ->
            val input = editable.toString()
            val newItems = items.filter { it.name!!.startsWith(input) }
            adapter.setItems(newItems)
            adapter.notifyDataSetChanged()
        }

        sheet_straight.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                adapter.straight = true
                adapter.notifyDataSetChanged()
            }
        }

        sheet_reverse.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                adapter.straight = false
                adapter.notifyDataSetChanged()
            }
        }

        (if(viewModel.straight)
            sheet_straight
        else
            sheet_reverse).isChecked = true

        val sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottom_sheet.setOnClickListener {
            sheetBehavior.state =
                if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED)
                    BottomSheetBehavior.STATE_EXPANDED
                else
                    BottomSheetBehavior.STATE_COLLAPSED
        }

        fab.setOnClickListener {
            val n = Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
            n.navigate(R.id.detailsFragment)
        }

        viewModel.hasChanges.observe(viewLifecycleOwner, Observer {
            adapter.notifyDataSetChanged()
        })
    }


    companion object {
        private const val ARGS_NAME = "args_name"
        private const val ARGS_TYPE = "args_type"

        fun newInstance(name: String, habitType: HabitType): ListFragment {
            val fragment = ListFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            bundle.putSerializable(ARGS_TYPE, habitType)
            fragment.arguments = bundle
            return fragment
        }
    }
}