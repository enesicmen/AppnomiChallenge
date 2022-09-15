package com.example.appnomichallenge.ui.fragment.productDetail

import androidx.lifecycle.ViewModel
import com.example.appnomichallenge.data.NetworkCallback
import com.example.appnomichallenge.data.Resource
import com.example.appnomichallenge.data.model.ProductDetail
import com.example.appnomichallenge.data.repository.ProductDetailRepository
import com.example.appnomichallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) : ViewModel() {

    var productDetailList: SingleLiveEvent<Resource<ProductDetail>> = SingleLiveEvent()

    fun getProductDetail(productId: String) {
        productDetailList.value = Resource.Loading()
        productDetailRepository.getProductDetail(productId,object : NetworkCallback<ProductDetail>{
            override fun onSuccess(data: ProductDetail) {
                productDetailList.value = Resource.Success(data)
            }
            override fun onError(message: String) {
                productDetailList.value = Resource.Error(message)
            }

        })
    }
}