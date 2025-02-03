package com.example.challengetask4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

abstract class BaseNavActivity : AppCompatActivity() {
    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var toolbar: androidx.appcompat.widget.Toolbar
    protected val viewModel: LaunchViewModel by viewModels()

    // Optional RecyclerView; Child activities can use it if needed
    protected open val hasRecyclerView: Boolean = true
    protected var recyclerView: RecyclerView? = null
    protected val adapter = LaunchAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base_nav)

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.navView)


        // Set up Toolbar as ActionBar
        setSupportActionBar(toolbar)

        // Set up Navigation Drawer
        setupNavigationDrawer(navView)

        // Set up DrawerToggle (hamburger icon)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Conditionally initialize RecyclerView
        if (hasRecyclerView) {
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView?.apply {
                layoutManager = LinearLayoutManager(this@BaseNavActivity)
                adapter = this@BaseNavActivity.adapter
            }
        }

        // Observe ViewModel for updates
        observeViewModel()
    }

    open fun setupNavigationDrawer(navView: NavigationView) {
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_upcoming -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                }

                R.id.nav_past -> {
                        val intent = Intent(this, PastLaunches::class.java)
                        startActivity(intent)
                }
            }
            // Close drawer when item is clicked
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun observeViewModel() {
        viewModel.launches.observe(this) { launches ->
            adapter.updateData(launches)
        }

        // Display toast if API fails to load data
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}