package com.example.appnomichallenge.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Categories (

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


): Parcelable