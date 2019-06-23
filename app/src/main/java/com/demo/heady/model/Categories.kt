package com.demo.heady.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Categories() {


    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    var id: Int = 0

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var categoryName: String? = null

    var parentId: Int = 0

    @SerializedName("products")
    @Expose
    @Ignore
    var products: List<Product>? = null

    @SerializedName("child_categories")
    @Expose
    @Ignore
    var childCategories: List<Int>? = null
}