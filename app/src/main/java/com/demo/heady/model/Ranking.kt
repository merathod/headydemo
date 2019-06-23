package com.demo.heady.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Ranking{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "m_id")
    var mId: Int=0

    @SerializedName("ranking")
    @ColumnInfo(name = "ranking")
    var rankingName : String?=null

    @SerializedName("products")
    @Ignore
    var productsRanking : List<ProductRanking>?=null
}
