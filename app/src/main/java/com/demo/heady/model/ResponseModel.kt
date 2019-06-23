package com.demo.heady.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("categories")
    @Expose
    var categories: List<Categories>? = null,

    @SerializedName("rankings")
    @Expose
    var rankings: List<Ranking>? = null
)