package com.example.homework3doubletapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    val hasChanges: LiveData<Boolean> = Transformations.map(Repository.hasChanges) { true }

    val goodHabits = Repository.goodHabits
    val badHabits = Repository.badHabits

    var straight = true
}