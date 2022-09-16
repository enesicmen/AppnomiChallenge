package com.example.appnomichallenge.data.repository

import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.api.ApiService
import com.example.appnomichallenge.data.api.response.CategoriesApiResponse
import com.example.appnomichallenge.data.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getCategories(callback: NetworkCallback<List<Category>>) {

        val call: Call<CategoriesApiResponse> = apiService.getCategories()
        call.enqueue(object : Callback<CategoriesApiResponse> {
            override fun onResponse(
                call: Call<CategoriesApiResponse>,
                response: Response<CategoriesApiResponse>
            ) {
                if (response.isSuccessful) {
                    val categoriesApiResponse = response.body()!!
                    callback.onSuccess(data = categoriesApiResponse.data)
                } else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<CategoriesApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }
}