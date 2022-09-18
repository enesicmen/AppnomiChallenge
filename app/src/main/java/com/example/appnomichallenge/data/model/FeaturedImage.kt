package com.example.appnomichallenge.data.model

import com.google.gson.annotations.SerializedName

data class FeaturedImage (

    @SerializedName("t")
    val thumbnail: String? = null,

    @SerializedName("n")
    val normal: String? = null

)