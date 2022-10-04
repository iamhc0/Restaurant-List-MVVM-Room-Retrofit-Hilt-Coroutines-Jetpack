package com.vikskod.restaurantlist.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.vikskod.restaurantlist.R
import com.vikskod.restaurantlist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavView, navHostFragment.navController)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.allRestaurantFragment, R.id.favouriteRestaurantFragment))
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        //Don't reload selected fragment
        binding.bottomNavView.setOnNavigationItemReselectedListener { }
    }

    fun showBottomNavigation() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavView.visibility = View.GONE
    }

    private var backPressedOnce = false

    override fun onBackPressed() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(2000) {
                backPressedOnce = false
            }
        } else super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}