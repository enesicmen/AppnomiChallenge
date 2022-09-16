package com.example.appnomichallenge.data.repository

import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.api.ApiService
import com.example.appnomichallenge.data.api.response.ProductsApiResponse
import com.example.appnomichallenge.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProducts(categoryId: String,sortParameter:String,callback: NetworkCallback<List<Product>>){

        val call: Call<ProductsApiResponse> = apiService.getProducts(categoryId,sortParameter)
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