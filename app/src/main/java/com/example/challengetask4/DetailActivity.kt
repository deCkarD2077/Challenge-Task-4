package com.example.challengetask4

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : BaseNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // Add this line to set the content view

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
}