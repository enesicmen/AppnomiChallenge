package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.ProductDetail

class ProductDetailApiResponse (
    val success: Boolean = true,
    val data: ProductDetail? = null
)