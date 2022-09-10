package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.Products

class ProductsApiResponse (
    val success: Boolean = true,
    val data: List<Products> = listOf(),
)