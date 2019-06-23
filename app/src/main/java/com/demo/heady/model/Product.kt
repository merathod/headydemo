package com.demo.heady.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
 class Product(){

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id") var id: Int?=null


   @SerializedName("name")
   @Expose
   @ColumnInfo(name = "name") var productName: String?=null

    @SerializedName("date_added")
    @Expose
    @ColumnInfo(name = "date_added") var dateAdded: String?=null

    @ColumnInfo(name = "order_count") var orderCount: Int = 0

    @ColumnInfo(name = "shares") var shares: Int = 0

    @ColumnInfo(name = "view_count") var viewCount: Int = 0

    @SerializedName("tax")
    @Expose
    @Embedded
    var tax: Tax?=null

    @SerializedName("variants")
    @Expose
    @Ignore
    var variants: List<Variant>? = null

   @ColumnInfo(name = "fk_categotyId")
   var fk_categotyId: Int=0



}