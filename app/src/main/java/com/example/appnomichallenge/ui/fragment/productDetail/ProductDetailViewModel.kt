package com.example.appnomichallenge.ui.fragment.productDetail

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Products
import com.example.appnomichallenge.data.repository.ProductsRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    var productsList: SingleLiveEvent<Resource<List<Products>>> = SingleLiveEvent()

    fun getProducts(categoryId: String){
        productsList.value = Resource.Loading()
        productsRepository.getProducts(categoryId,object : NetworkCallback<List<Products>>{
            override fun onSuccess(data: List<Products>) {
                productsList.value = Resource.Success(data)
            }

            override fun onError(message: String) {
                productsList.value = Resource.Error(message)
            }

        })
    }
}