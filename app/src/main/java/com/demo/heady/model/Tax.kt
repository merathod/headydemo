package com.demo.heady.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tax(

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "tax_name")
    var name: String,

    @SerializedName("value")
    @Expose
    @ColumnInfo(name = "tax_value")
    var value: Float
)