package com.demo.heady.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.heady.R
import com.demo.heady.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(val mProductList: List<Product>, val callback: (Int) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mProductList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val product = mProductList[position]
        view.tvProduct.text = product.productName

        view.setOnClickListener {
            callback(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}