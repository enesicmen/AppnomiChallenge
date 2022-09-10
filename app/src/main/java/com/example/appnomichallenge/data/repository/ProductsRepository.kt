package com.example.appnomichallenge.data.repository

import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.api.ApiService
import com.example.appnomichallenge.data.api.response.ProductsApiResponse
import com.example.appnomichallenge.data.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProducts(categoryId: String,callback: NetworkCallback<List<Products>>){

        val call: Call<ProductsApiResponse> = apiService.getProducts(categoryId)
        call.enqueue(object : Callback<ProductsApiResponse>{
            override fun onResponse(
                call: Call<ProductsApiResponse>,
                response: Response<ProductsApiResponse>
            ) {
                if(response.isSuccessful){
                    val productsApiResponse = response.body()!!
                    callback.onSuccess(data = productsApiResponse.data)
                }else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<ProductsApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }

        })
    }
}