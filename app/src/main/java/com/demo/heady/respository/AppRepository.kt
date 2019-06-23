package com.demo.heady.respository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.heady.db.AppDatabase
import com.demo.heady.model.Categories
import com.demo.heady.model.Product
import com.demo.heady.model.ResponseModel
import com.demo.heady.network.ApiClient

class AppRepository(var app: Application) {

    val dao = AppDatabase.getDatabase(app).appDao()

    fun callLoadDataAPI(responseLiveData: MutableLiveData<ResponseModel>, errorLiveData: MutableLiveData<String>) {


        if (dao.isNotEmpty()) {
            // added this line to give callback to splash screen
            responseLiveData.postValue(ResponseModel())
            return;
        }

        val callAPI = ApiClient.apiService.getData()

        ApiClient.call(callAPI, successCallback = {
            dao.fillAllTablesData(it)
            responseLiveData.postValue(it)
            dao.updateProduct(it)
        }, errorCallback = {
            errorLiveData.value = ("Something went wrong")
        })


    }





}