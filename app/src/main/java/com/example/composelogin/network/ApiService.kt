package com.example.composelogin.network

import com.example.composelogin.model.RandomUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("apis")
    suspend fun getRandomUsers(): List<RandomUser>

    companion object{
        var apiService: ApiService? = null
        fun getInstance(): ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://randomuser.me/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
             return apiService!!
        }
    }
}