package com.example.appnomichallenge.data.model

import com.google.gson.annotations.SerializedName

class ProductDetail {

    @SerializedName("title")
    val title: String? = null

    @SerializedName("price")
    val price: Double? = null

    @SerializedName("campaignPrice")
    val campaignPrice: Double? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("stock")
    val stock: Int? = null

    @SerializedName("currency")
    val currency: String? = null

    @SerializedName("createDate")
    val createDate: String? = null

    @SerializedName("images")
    val productsImage: List<ProductDetailImage> = arrayListOf()
}