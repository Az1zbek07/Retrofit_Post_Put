package com.example.retrofitpostputget.network

import com.example.retrofitpostputget.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/recipes/complexSearch")
    fun getFoods(
        @Query("apiKey") apiKey: String = "754b79e5a34b441a81e0e83a0e5286c3",
    ): Call<FoodResponse>

    @GET("/recipes/complexSearch")
    fun searchFood(
        @Query("apiKey") apiKey: String = "754b79e5a34b441a81e0e83a0e5286c3",
        @Query("query") query: String
    ): Call<FoodResponse>
}