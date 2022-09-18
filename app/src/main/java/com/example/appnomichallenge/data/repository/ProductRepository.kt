package com.example.appnomichallenge.data.repository

import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.api.ApiService
import com.example.appnomichallenge.data.api.response.ProductDetailApiResponse
import com.example.appnomichallenge.data.api.response.ProductsApiResponse
import com.example.appnomichallenge.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
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