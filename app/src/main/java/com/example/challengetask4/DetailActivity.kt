package com.example.challengetask4

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DetailActivity : BaseNavActivity() {
    override val hasRecyclerView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.navView)
        // Set up Navigation Drawer
        setupNavigationDrawer(navView)

        // Get the Launch object from the intent
        val launch = intent.getParcelableExtra<Launch>("launch_data")
        if (launch == null) {
            Toast.makeText(this, "Failed to load launch details", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Find and set the views
        findViewById<TextView>(R.id.title).text = launch.mission_name
        findViewById<TextView>(R.id.launchDate).text = "Launch Date: ${launch.launch_date_utc}"
        findViewById<TextView>(R.id.rocket).text = "Rocket: ${launch.rocket.rocket_name} (${launch.rocket.rocket_type})"
        findViewById<TextView>(R.id.launchSite).text = "Launch Site: ${launch.launch_site.site_name}"
        findViewById<TextView>(R.id.success).text = "Launch Success: ${if (launch.success == true) "Yes" else "No"}"

        val detailsTextView = findViewById<TextView>(R.id.details)
        detailsTextView.text = if (!launch.details.isNullOrEmpty()) {
            "${launch.details}"
        } else {
            "No additional details available."
        }
    }

    override fun setupNavigationDrawer(navView: NavigationView) {
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
}