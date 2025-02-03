package com.example.challengetask4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapp.network.RetrofitClient
import kotlinx.coroutines.launch

class LaunchViewModel : ViewModel(){
    private val _launches = MutableLiveData<List<Launch>>()
    val launches: LiveData<List<Launch>> = _launches

    private val _isLoading = MutableLiveData<Boolean>()

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchUpcomingLaunches() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val launches = RetrofitClient.api.getUpcomingLaunches()
                _launches.value = launches
            } catch (e: Exception) {
                Log.e("LaunchViewModel", "Error fetching upcoming launches", e)
                _errorMessage.value = "Failed to load upcoming launches. Please check your connection."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchPastLaunches() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val launches = RetrofitClient.api.getPastLaunches()
                _launches.value = launches
            } catch (e: Exception) {
                Log.e("LaunchViewModel", "Error fetching past launches", e)
                _errorMessage.value = "Failed to load past launches. Please check your connection."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
