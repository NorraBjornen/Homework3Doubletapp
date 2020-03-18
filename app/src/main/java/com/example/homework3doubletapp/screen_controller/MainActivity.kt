package com.example.homework3doubletapp.screen_controller

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3doubletapp.R
import com.example.homework3doubletapp.screens.AboutFragment
import com.example.homework3doubletapp.screens.DetailsFragment
import com.example.homework3doubletapp.screens.ListFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    FragmentChanger, NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.HSV, R.string.RGB)

        drawer_layout.addDrawerListener(drawerToggle)

        navigation_drawer.setNavigationItemSelectedListener(this)

        val fragment =
            ListFragment.newInstance(
                "some name"
            )

        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .add(R.id.main_container, fragment, "list_fragment")
            .commit()
    }

    override fun startDetailsScreen(habitName : String?) {
        val fragment =
            DetailsFragment.newInstance(
                "some other name",
                habitName
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "details_fragment")
            .addToBackStack("backstack")
            .commit()
    }

    override fun startMainScreen() {
        val fragment = ListFragment.newInstance("some name")

        supportFragmentManager.popBackStackImmediate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "list_fragment")
            .commit()
    }

    override fun startAboutScreen() {
        val fragment = AboutFragment.newInstance("some name")

        supportFragmentManager.popBackStackImmediate()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, "about_fragment")
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main -> {
                startMainScreen()
                return true
            }
            R.id.about -> {
                startAboutScreen()
                return true
            }
            else -> {
                Toast.makeText(this, "MEMES", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return false
    }


}