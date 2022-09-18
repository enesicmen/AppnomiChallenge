package com.example.appnomichallenge.ui.productlist

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.data.repository.ProductRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductRepository
) : ViewModel() {

    var productList: SingleLiveEvent<Resource<List<Product>>> = SingleLiveEvent()

    fun getProducts(categoryId: String,sortParameter:String){
        productList.value = Resource.Loading()
        productsRepository.getProducts(categoryId,sortParameter,object : NetworkCallback<List<Product>> {
            override fun onSuccess(data: List<Product>) {
                productList.value = Resource.Success(data)
            }
            override fun onError(message: String) {
                productList.value = Resource.Error(message)
            }
        })
    }
}