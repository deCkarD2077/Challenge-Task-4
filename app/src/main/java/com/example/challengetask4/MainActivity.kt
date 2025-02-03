package com.example.challengetask4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private val  viewModel: LaunchViewModel by viewModels()
    private val adapter = LaunchAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //initialise views
        drawerLayout = findViewById(R.id.drawerLayout)
        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.navView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Setup Toolbar as ActionBar
        setSupportActionBar(toolbar)
        //setup the navigation drawer
        setupNavigationDrawer(navView)
        //observe view model
        observeViewModel()
        //initial data load

        // Setup the DrawerToggle (hamburger icon)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Observe the ViewModel for updates
        observeViewModel()

        viewModel.fetchUpcomingLaunches()
    }

    private fun setupNavigationDrawer(navView: NavigationView) {
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

        //display toast if api fails to load data
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}