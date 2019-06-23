package com.demo.heady.respository

import android.app.Application
import com.demo.heady.db.AppDatabase

class MainRepository (app : Application){

    val dao = AppDatabase.getDatabase(app).appDao()

    fun getCategoriesListFromDB(parentId : Int =0) = dao.getCategoryList(parentId)
}