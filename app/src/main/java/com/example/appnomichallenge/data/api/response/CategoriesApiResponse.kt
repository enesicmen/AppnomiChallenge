package com.example.appnomichallenge.data.api.response

import com.example.appnomichallenge.data.model.CategoriesModel

class CategoriesApiResponse (
    val success: Boolean = true,
    val data: List<CategoriesModel> = listOf(),
)