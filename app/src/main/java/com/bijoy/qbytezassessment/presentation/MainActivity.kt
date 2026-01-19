package com.bijoy.qbytezassessment.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.databinding.ActivityMainBinding
import com.bijoy.qbytezassessment.presentation.dashboard.DashboardFragment
import kotlin.jvm.java

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment

        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu()

            binding.toolbar.title = when (destination.id) {
                R.id.dashboardFragment -> "Dashboard"
                R.id.productDetailsFragment -> "Product Detail"
                R.id.cartFragment -> "My Cart"
                else -> getString(R.string.app_name)
            }

            supportActionBar?.setDisplayHomeAsUpEnabled(
                destination.id != R.id.dashboardFragment
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.fragmentContainer).navigateUp()
                true
            }
            R.id.action_cart -> {
                findNavController(R.id.fragmentContainer).navigate(R.id.cartFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        val isCartScreen =
            navController.currentDestination?.id == R.id.cartFragment

        menu.findItem(R.id.action_cart)?.isVisible = !isCartScreen
        return super.onPrepareOptionsMenu(menu)
    }

}