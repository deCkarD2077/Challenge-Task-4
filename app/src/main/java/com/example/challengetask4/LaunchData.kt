package com.example.challengetask4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Launch(
    val mission_name: String,
    val launch_date_utc: String,
    val rocket: Rocket,
    val launch_site: LaunchSite,
    val success: Boolean?,
    val details: String
) : Parcelable

@Parcelize
data class Rocket(
    val rocket_name: String,
    val rocket_type: String
) : Parcelable

@Parcelize
data class LaunchSite(
    val site_name: String
) : Parcelable