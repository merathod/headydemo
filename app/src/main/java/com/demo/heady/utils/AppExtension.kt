package com.demo.heady.utils

import android.view.View
import android.widget.TextView


fun TextView.showColor(color: String){
    if(color.isNullOrEmpty()){
        this.visibility=View.GONE
    }else{
        this.visibility = View.VISIBLE
        this.text = "Color : ${color}"
    }
}

fun TextView.showPrice(price: Int){
    if(price==0){
        this.visibility=View.GONE
    }else{
        this.visibility = View.VISIBLE
        this.text = "Price : ${price}"
    }
}

fun TextView.showSize(size: Int){
    if(size==0){
        this.visibility=View.GONE
    }else{
        this.visibility = View.VISIBLE
        this.text = "Size : ${size}"
    }
}