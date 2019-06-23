package com.demo.heady.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.demo.heady.model.ResponseModel
import com.demo.heady.respository.AppRepository

class SplashViewModel(val app: Application) : AndroidViewModel(app){

    val responseLiveData  = MutableLiveData<ResponseModel>()
    val errorResponseLiveData  = MutableLiveData<String>()

    init {
        errorResponseLiveData.value = "Loading..."
    }

    fun callLoadDataAPI(){
        val appRepository = AppRepository(app)
        appRepository.callLoadDataAPI(responseLiveData,errorResponseLiveData)
    }




}