package com.aliyoungprog.presentation.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aliyoungprog.R
import com.aliyoungprog.presentation.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavBar()
    }

    private fun changeFragment(fragmentObject: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.nav_host_fragment, fragmentObject)
        fragmentManager.commit()
    }

    private fun setupBottomNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(HomeFragment.getInstance())
                }
                R.id.navigation_forecast -> {
                    changeFragment(WeatherFragment.getInstance())
                }
                R.id.navigation_library -> {
                    changeFragment(AllProductsFragment.getInstance())
                }
                R.id.navigation_calendar -> {
                    changeFragment(MoonCalendarFragment.getInstance())
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
