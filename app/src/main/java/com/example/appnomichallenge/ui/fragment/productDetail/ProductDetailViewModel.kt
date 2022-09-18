package com.example.appnomichallenge.ui.fragment.productDetail

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.data.repository.ProductRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var productDetailList: SingleLiveEvent<Resource<Product>> = SingleLiveEvent()

    fun getProductDetail(productId: String) {
        productDetailList.value = Resource.Loading()
        productRepository.getProductDetail(productId,object : NetworkCallback<Product>{
            override fun onSuccess(data: Product) {
                productDetailList.value = Resource.Success(data)
            }
            override fun onError(message: String) {
                productDetailList.value = Resource.Error(message)
            }
        })
    }
}