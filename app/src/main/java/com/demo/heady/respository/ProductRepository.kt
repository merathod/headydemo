package com.demo.heady.respository

import android.app.Application
import com.demo.heady.db.AppDatabase

class ProductRepository (app : Application){

    val dao = AppDatabase.getDatabase(app).appDao()

    fun getProductListFromDB(categoryId : Int =0) = dao.getProductList(categoryId)

    fun getVariantsListFromDB(productId : Int = 0) = dao.getVariantsList(productId)

    fun getFilterListFromDB(filterType : String, categoryId : Int =0) = dao.sortByFields(filterType,categoryId)
}