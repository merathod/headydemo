package com.demo.heady.model

import com.google.gson.annotations.SerializedName

data class ProductRanking(
    @SerializedName("id")
    val id: Int?=null,

    @SerializedName("view_count")
    val viewCount: Int?=null,

    @SerializedName("order_count")
    val orderCount: Int?=null,

    @SerializedName("shares")
    val shares: Int?=null

)