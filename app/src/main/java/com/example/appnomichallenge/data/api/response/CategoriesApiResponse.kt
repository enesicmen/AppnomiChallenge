package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.Category

class CategoriesApiResponse (
    val success: Boolean = true,
    val data: List<Category> = listOf(),
)