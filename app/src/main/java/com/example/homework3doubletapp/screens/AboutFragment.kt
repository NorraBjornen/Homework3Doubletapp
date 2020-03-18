package com.example.homework3doubletapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework3doubletapp.R

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_about, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name : String) : AboutFragment{
            val fragment =  AboutFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_NAME, name)
            fragment.arguments = bundle
            return fragment
        }
    }
}