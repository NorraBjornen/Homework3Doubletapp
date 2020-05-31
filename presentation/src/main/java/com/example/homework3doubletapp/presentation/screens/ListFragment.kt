package com.example.homework3doubletapp.presentation.screens

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.domain.entities.HabitType
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.LoadHabitsUseCase
import com.example.homework3doubletapp.presentation.BottomSheetFragment
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.presentation.App
import com.example.homework3doubletapp.presentation.viewmodels.ListViewModel
import com.example.homework3doubletapp.presentation.recycler.Adapter
import com.example.homework3doubletapp.presentation.recycler.MyItemTouchCallback
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter

    @Inject
    lateinit var load: LoadHabitsUseCase
    @Inject
    lateinit var get: GetHabitsUseCase
    @Inject
    lateinit var delete: DeleteHabitUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).applicationComponent.inject(this)

        viewModel = ViewModelProvider(requireActivity(), @Suppress("UNCHECKED_CAST") object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel(get, delete, load) as T
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

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

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