package com.example.appnomichallenge.data.api

import com.example.appnomichallenge.data.api.response.CategoriesApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    fun getCategories(): Call<CategoriesApiResponse>
}