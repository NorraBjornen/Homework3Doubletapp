package com.example.homework3doubletapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val mutableFilterAndOrderChanged = MutableLiveData<Boolean>()
    val filterAndOrderChanged: LiveData<Boolean> = mutableFilterAndOrderChanged

    var straightOrder = true
    var filterString = ""

    fun filter(string: String) {
        filterString = string
        mutableFilterAndOrderChanged.value = true
    }

    fun changeOrder(straight: Boolean) {
        straightOrder = straight
        mutableFilterAndOrderChanged.value = true
    }

    fun getHabits(): LiveData<List<Habit>> {
        return Repository.get().getHabitsLiveData()
    }
}