package com.demo.heady.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.demo.heady.model.Categories
import com.demo.heady.respository.MainRepository

class CategoriesViewModel(val app: Application) : AndroidViewModel(app) {

    var categoriesLiveData: List<Categories>? = null

    fun getCategoriesListFromDB():List<Categories> {
        categoriesLiveData = MainRepository(app).getCategoriesListFromDB()
        return categoriesLiveData!!
    }

    fun getSubCategoriesFromCategories(parentId : Int) : List<Categories>{
        categoriesLiveData = MainRepository(app).getCategoriesListFromDB(parentId)
        return categoriesLiveData!!
    }

}
