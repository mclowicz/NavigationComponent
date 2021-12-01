package com.mclowicz.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.mclowicz.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationComponents()
    }

    private fun initNavigationComponents() {
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.destination_home),
            binding.drawerLayout
        )
        findNavController(R.id.my_nav_host_fragment).apply {
            setupActionBarWithNavController(this, appBarConfiguration)
                with (binding) {
                    drawerView.setupWithNavController(this@apply)
                    bottomNavView.setupWithNavController(this@apply)
                }
            addOnDestinationChangedListener { _, destination, _ ->
                with (binding) {
                    bottomNavView.visibility = View.VISIBLE
                    toolbar.menu.findItem(R.id.destination_settings)?.isVisible = true
                }
                when (destination.id) {
                    R.id.destination_home -> { binding.bottomNavView.visibility = View.GONE }
                    R.id.destination_settings -> {
                        binding.bottomNavView.visibility = View.GONE
                        binding.toolbar.menu.findItem(R.id.destination_settings).isVisible = false
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp(appBarConfiguration)
    }
}