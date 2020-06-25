package com.example.homework3doubletapp.presentation.ui

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.example.homework3doubletapp.R

class ListScreen: Screen<ListScreen>() {
    val fab = KView {
        withId(R.id.fab)
    }
}