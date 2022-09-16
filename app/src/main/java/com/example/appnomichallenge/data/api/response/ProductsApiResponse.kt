package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.Product

class ProductsApiResponse (
    val success: Boolean = true,
    val data: List<Product> = listOf(),
)