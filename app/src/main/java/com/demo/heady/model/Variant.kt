package com.demo.heady.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Variant(


) {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = "color")
    @SerializedName("color")
    @Expose
    var color: String? = null

    @ColumnInfo(name = "size")
    @SerializedName("size")
    @Expose
    var size: Int = 0

    @ColumnInfo(name = "price")
    @SerializedName("price")
    @Expose
    var price: Int = 0

    @ColumnInfo(name = "fk_productId")
    var fkProductId: Int? = null
}