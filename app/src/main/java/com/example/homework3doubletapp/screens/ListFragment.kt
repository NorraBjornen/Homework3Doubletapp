package com.example.homework3doubletapp.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.model.Repository
import com.example.homework3doubletapp.recycler.Adapter
import com.example.homework3doubletapp.screen_controller.FragmentChanger
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    var adapter : Adapter? = null

    var callback : FragmentChanger? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as FragmentChanger
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            callback?.startDetailsScreen(null)
        }

        val decoration =
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)

        adapter = Adapter(Repository.habits)


        recycler.adapter = adapter
    }

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name : String) : ListFragment{
            val fragment =  ListFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        Repository.habits.sortBy { it.priority }
        adapter?.notifyDataSetChanged()
    }
}