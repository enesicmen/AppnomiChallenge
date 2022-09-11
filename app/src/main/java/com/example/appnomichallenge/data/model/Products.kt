package com.example.appnomichallenge.data.model

import com.google.gson.annotations.SerializedName

class Products {

    @SerializedName("title")
    val title: String? = null

    @SerializedName("price")
    val price: Double? = null

    @SerializedName("campaignPrice")
    val campaignPrice: Double? = null

    @SerializedName("currency")
    val currency: String? = null

    @SerializedName("createDate")
    val createDate: String? = null

    @SerializedName("images")
    val productsImage: List<ProductsImage> = arrayListOf()

}


