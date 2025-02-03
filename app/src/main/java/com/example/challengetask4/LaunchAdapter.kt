package com.example.challengetask4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LaunchAdapter(private var launches: List<Launch> = emptyList()) :
    RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {

    class LaunchViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val missionName: TextView = view.findViewById(R.id.missionName)
        private val rocketName: TextView = view.findViewById(R.id.rocketName)
        private val launchDate: TextView = view.findViewById(R.id.launchDate)

        fun bind(launch: Launch) {
            missionName.text = launch.mission_name
            rocketName.text = launch.rocket.rocket_name
            launchDate.text = launch.launch_date_utc
        }
    }

    fun updateData(newLaunches: List<Launch>) {
        launches = newLaunches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_launch, parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount() = launches.size
}
