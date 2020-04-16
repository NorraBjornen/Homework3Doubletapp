package com.example.homework3doubletapp.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3doubletapp.BottomSheetFragment
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.addDivider
import com.example.homework3doubletapp.model.HabitType
import com.example.homework3doubletapp.model.ListViewModel
import com.example.homework3doubletapp.model.Repository
import com.example.homework3doubletapp.recycler.Adapter
import com.example.homework3doubletapp.recycler.MyItemTouchCallback
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), @Suppress("UNCHECKED_CAST") object : ViewModelProvider.Factory {
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

        val itemTouchHelper = ItemTouchHelper(MyItemTouchCallback(viewModel))
        itemTouchHelper.attachToRecyclerView(recycler)

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
                    list.filter { it.type == HabitType.GOOD.value }
                } else {
                    list.filter { it.type == HabitType.BAD.value }
                }

            adapter.setItems(actualItems)
        })

        viewModel.filterString.observe(viewLifecycleOwner, Observer {
            adapter.actualizeItems(it)
        })

        viewModel.straightOrder.observe(viewLifecycleOwner, Observer {
            adapter.actualizeItems(it)
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