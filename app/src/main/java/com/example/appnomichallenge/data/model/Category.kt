package com.example.appnomichallenge.data.model
import com.google.gson.annotations.SerializedName

class Category (

    @SerializedName("categoryId")
    val categoryId: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("icon")
    val icon: String? = null,

    @SerializedName("createDate")
    val createDate: String? = null,

    @SerializedName("isActive")
    val isActive: Boolean = false,


)