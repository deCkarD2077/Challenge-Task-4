package com.example.spacexapp.network

import com.example.challengetask4.SpaceXApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.spacexdata.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SpaceXApiService by lazy {
        retrofit.create(SpaceXApiService::class.java)
    }
}
