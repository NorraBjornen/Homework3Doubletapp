package com.example.homework3doubletapp.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_details.*
import org.junit.Rule
import org.junit.Test


class UITest {

    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)

    @Test
    fun test() {

        onScreen<ListScreen> {
            fab.click()
        }

        onScreen<DetailsScreen> {
            name.replaceText("Make")
            desc.replaceText("Tests")
            quantity.replaceText("99")
            period.replaceText("7")
            onView(withId(R.id.selected_color + 3)).perform(click())
            goodRadio.click()
            save.click()
        }
    }
}