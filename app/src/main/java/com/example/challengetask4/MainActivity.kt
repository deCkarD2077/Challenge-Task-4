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

class MainActivity : BaseNavActivity() {
    override val hasRecyclerView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set title for the activity
        supportActionBar?.title = "Upcoming Launches"

        // Fetch upcoming launches
        viewModel.fetchUpcomingLaunches()

        // Observe launches data
        viewModel.launches.observe(this) { launches ->
            adapter.updateData(launches)
        }
    }
}