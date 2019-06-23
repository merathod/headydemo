package com.demo.heady.network


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class used to generate APi client with its configuration
 *
 * It will return [ApiService] to handle api
 */
object ApiClient {

    private var retrofit: Retrofit? = null


    /**
     * Return [ApiService] that represent api services.
     * This method used to create retrofit single instance with all necessary
     * configuration.
     *
     * @return [ApiService]
     */
    val apiService: ApiService
        get() {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)

            retrofit = Retrofit.Builder()
                .baseUrl("https://stark-spire-93433.herokuapp.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit?.create(ApiService::class.java)!!
        }


    fun <T> call(
        call: Observable<T>,
        successCallback: (response: T) -> Unit,
        errorCallback: (error: Throwable) -> Unit
    ) {
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: T) {
                    successCallback(t);
                }

                override fun onError(e: Throwable) {
                    errorCallback(e)
                }

                override fun onComplete() {

                }
            })
    }
}
