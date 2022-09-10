package com.example.appnomichallenge.data.api

import com.example.appnomichallenge.data.api.response.CategoriesApiResponse
import com.example.appnomichallenge.data.api.response.ProductsApiResponse
import com.example.appnomichallenge.data.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories")
    fun getCategories(): Call<CategoriesApiResponse>

    @GET("products/advanced-filtered")
    fun getProducts(@Query("categoryId") categoryId: String): Call<ProductsApiResponse>
}