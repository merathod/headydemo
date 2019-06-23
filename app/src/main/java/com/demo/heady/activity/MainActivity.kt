package com.demo.heady.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.heady.R
import com.demo.heady.adapter.CategoriesAdapter
import com.demo.heady.databinding.ActivityMainBinding
import com.demo.heady.model.Categories
import com.demo.heady.utils.EXTRA_CATEGORY_ID
import com.demo.heady.viewModel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mCategoriesAdapter: CategoriesAdapter
    private var mCategoriesList = ArrayList<Categories>()
    private var parentIdList = arrayListOf<Int>()
    private var categoryId  =0


    private val categoriesViewModel: CategoriesViewModel by lazy {
        ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.categoriesViewModel = categoriesViewModel
        mainBinding.lifecycleOwner = this
        setCategoriesAdapter()
        getCategoriesList()
    }

    private fun getCategoriesList() {
        mCategoriesList.addAll(categoriesViewModel.getCategoriesListFromDB())
        mCategoriesAdapter.notifyDataSetChanged()
    }


    private fun setCategoriesAdapter() {
        mCategoriesAdapter = CategoriesAdapter(mCategoriesList) { position ->
           categoryId = mCategoriesList[position].id
            parentIdList.add(mCategoriesList[position].parentId)

            if(categoriesViewModel.getSubCategoriesFromCategories(categoryId).isEmpty()){
                //get Product Detail
                startActivity(Intent(this, ProductDetailActivity::class.java)
                    .putExtra(EXTRA_CATEGORY_ID,categoryId))
            }else{
                mCategoriesList.clear()
                mCategoriesList.addAll(categoriesViewModel.getSubCategoriesFromCategories(categoryId))
                mCategoriesAdapter.notifyDataSetChanged()
            }
        }
        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = mCategoriesAdapter
    }


    override fun onBackPressed() {
        if(parentIdList.isNotEmpty()){
            val parentId = parentIdList.get(parentIdList.size -1)
            parentIdList.remove(parentId)
            mCategoriesList.clear()
            mCategoriesList.addAll(categoriesViewModel.getSubCategoriesFromCategories(parentId))
            mCategoriesAdapter.notifyDataSetChanged()
        }else{
            super.onBackPressed()
        }

    }

}
