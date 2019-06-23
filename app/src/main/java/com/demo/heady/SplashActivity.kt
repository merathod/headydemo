package com.demo.heady

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.heady.activity.MainActivity
import com.demo.heady.databinding.ActivitySplashBinding
import com.demo.heady.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        splashBinding.splashViewModel =splashViewModel
        splashBinding.lifecycleOwner = this

        addObserver()

        splashViewModel.callLoadDataAPI()
    }

    fun addObserver(){
        splashViewModel.responseLiveData.observe(this, Observer {
            splashBinding.pbLoading.visibility=View.GONE
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        })

        splashViewModel.errorResponseLiveData.observe(this, Observer {
             Log.e("errp",it)
        })
    }

}
