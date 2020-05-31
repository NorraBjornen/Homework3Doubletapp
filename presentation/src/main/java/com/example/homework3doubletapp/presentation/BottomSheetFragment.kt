package com.example.homework3doubletapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.DeleteHabitUseCase
import com.example.domain.usecases.GetHabitsUseCase
import com.example.domain.usecases.LoadHabitsUseCase
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.presentation.viewmodels.ListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import javax.inject.Inject

class BottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var viewModel: ListViewModel

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
    ): View? = inflater.inflate(R.layout.bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sheet_search.setText(viewModel.filterString.value)

        sheet_search.addTextChangedListener {editable ->
            val input = editable.toString()
            viewModel.filter(input)
        }

        sheet_straight.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                viewModel.changeOrder(true)
            }
        }

        sheet_reverse.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                viewModel.changeOrder(false)
            }
        }

        (if(viewModel.straightOrder.value != false)
            sheet_straight
        else
            sheet_reverse).isChecked = true
    }
}