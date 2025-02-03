package com.example.challengetask4

data class Launch(
    val mission_name: String,
    val launch_date_utc: String,
    val rocket: Rocket,
    val launch_site: LaunchSite,
    val success: Boolean?
)

data class Rocket(
    val rocket_name: String,
    val rocket_type: String
)

data class LaunchSite(
    val site_name: String
)