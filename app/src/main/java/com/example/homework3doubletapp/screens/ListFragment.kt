package com.example.homework3doubletapp.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.homework3doubletapp.App
import com.example.homework3doubletapp.BottomSheetFragment
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.addDivider
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.ListViewModel
import com.example.homework3doubletapp.recycler.Adapter
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
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
        recycler.adapter = adapter

        bottom_sheet.setOnClickListener {
            BottomSheetFragment().show(
                requireActivity().supportFragmentManager,
                null
            )
        }

        fab.setOnClickListener {
            val n = Navigation.findNavController(activity as Activity, R.id.nav_host_fragment)
            n.navigate(R.id.detailsFragment)
        }

        viewModel.getHabits().observe(viewLifecycleOwner, Observer { list ->
            val actualItems =
                if (habitType == HabitType.GOOD) {
                    list.filter { it.type == HabitType.GOOD }
                } else {
                    list.filter { it.type == HabitType.BAD }
                }

            adapter.setItems(actualItems)
        })

        viewModel.filterAndOrderChanged.observe(viewLifecycleOwner, Observer {
            adapter.actualizeItems(viewModel.filterString, viewModel.straightOrder)
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