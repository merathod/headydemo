package com.demo.heady.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.demo.heady.model.Product
import com.demo.heady.model.Variant
import com.demo.heady.respository.ProductRepository

class ProductDetailViewModel(val app: Application) : AndroidViewModel(app){

    var productList :List<Product>?= null
    var variantList : List<Variant>?=null

    var isToShowVariant = MutableLiveData<Boolean>()

    init {
        isToShowVariant.value = false
    }

    fun getProductListFromDB(categoryId : Int): List<Product>{
        productList = ProductRepository(app).getProductListFromDB(categoryId)
        return productList!!
    }


    fun getProductVariantFromDB(productId: Int) : List<Variant>{
        variantList = ProductRepository(app).getVariantsListFromDB(productId)
        return variantList!!
    }

    fun getProductByFilter(filterFiled : String , mCategoryId : Int): List<Product>{
        productList = ProductRepository(app).getFilterListFromDB(filterFiled,mCategoryId)
        return productList!!
    }


}