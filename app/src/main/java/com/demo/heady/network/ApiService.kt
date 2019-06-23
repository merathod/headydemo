package com.demo.heady.network

import com.demo.heady.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {
@GET("json")
fun getData() : Observable<ResponseModel>
}