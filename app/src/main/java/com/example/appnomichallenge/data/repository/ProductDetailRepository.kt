package com.example.appnomichallenge.data.repository

import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.api.ApiService
import com.example.appnomichallenge.data.api.response.ProductDetailApiResponse
import com.example.appnomichallenge.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProductDetail(productId: String,callback: NetworkCallback<Product>) {

        val call: Call<ProductDetailApiResponse> = apiService.getProductDetail(productId)
        call.enqueue(object : Callback<ProductDetailApiResponse>{
            override fun onResponse(
                call: Call<ProductDetailApiResponse>,
                response: Response<ProductDetailApiResponse>
            ) {
                if(response.isSuccessful && response.body()?.data != null){
                    callback.onSuccess(data = response.body()!!.data!!)
                }else {
                    callback.onError(message = response.message())
                }
            }
            override fun onFailure(call: Call<ProductDetailApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }
}