package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.Categories

class CategoriesApiResponse (
    val success: Boolean = true,
    val data: List<Categories> = listOf(),
)