package com.demo.heady.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.heady.R
import com.demo.heady.model.Categories
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter(val mCategoriesList: List<Categories>, val callback: (Int) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = mCategoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val category = mCategoriesList[position]
        view.tvCategoriesName.text = category.categoryName

        view.setOnClickListener {
            callback(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}