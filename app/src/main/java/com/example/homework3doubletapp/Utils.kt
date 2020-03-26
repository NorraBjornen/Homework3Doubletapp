package com.example.homework3doubletapp

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDivider(context: Context) {
    this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}