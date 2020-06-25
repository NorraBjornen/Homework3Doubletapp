package com.example.homework3doubletapp.presentation.ui

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.example.homework3doubletapp.R

class DetailsScreen: Screen<DetailsScreen>() {
    val name = KEditText {
        withId(R.id.name_edit)
    }

    val desc = KEditText {
        withId(R.id.description_edit)
    }

    val spinner = KView {
        withId(R.id.priority_spinner)
    }

    val quantity = KEditText {
        withId(R.id.quantity_edit)
    }

    val period = KEditText {
        withId(R.id.period_edit)
    }

    val save = KView {
        withId(R.id.save)
    }

    val goodRadio = KView {
        withId(R.id.good_radio)
    }
}