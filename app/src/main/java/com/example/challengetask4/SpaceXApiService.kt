package com.example.challengetask4

import retrofit2.http.GET

interface SpaceXApiService {
    @GET("v3/launches/upcoming")
    suspend fun getUpcomingLaunches(): List<Launch>

    @GET("v3/launches/past")
    suspend fun getPastLaunches(): List<Launch>
}