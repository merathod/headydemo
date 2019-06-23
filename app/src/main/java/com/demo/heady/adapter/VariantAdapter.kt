package com.demo.heady.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.heady.R
import com.demo.heady.model.Variant
import com.demo.heady.utils.showColor
import com.demo.heady.utils.showPrice
import com.demo.heady.utils.showSize
import kotlinx.android.synthetic.main.item_variant.view.*

class VariantAdapter(val mVariantList: List<Variant>) : RecyclerView.Adapter<VariantAdapter.ViewHolder>() {

    private var productName =""

    override fun getItemCount(): Int = mVariantList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val variant = mVariantList[position]

        view.tvColor.showColor(variant.color!!)
        view.tvPrice.showPrice(variant.price)
        view.tvSize.showSize(variant.size)
        view.tvProductName.text= productName


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_variant, parent, false))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setProductName(productItem : String){
        productName = productItem
    }
}