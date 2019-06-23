package com.demo.heady.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.heady.R
import com.demo.heady.adapter.ProductAdapter
import com.demo.heady.adapter.VariantAdapter
import com.demo.heady.databinding.ActivityProductDetailBinding
import com.demo.heady.model.Product
import com.demo.heady.model.Variant
import com.demo.heady.utils.COLUMN_ORDER_COUNT
import com.demo.heady.utils.COLUMN_SHARES
import com.demo.heady.utils.COLUMN_VIEW_COUNT
import com.demo.heady.utils.EXTRA_CATEGORY_ID
import com.demo.heady.viewModel.ProductDetailViewModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.util.ArrayList

class ProductDetailActivity : AppCompatActivity() {


    private lateinit var productDetailBinding: ActivityProductDetailBinding
    private val productDetailViewModel: ProductDetailViewModel by lazy {
        ViewModelProviders.of(this).get(ProductDetailViewModel::class.java)
    }

    private var mCategoryId = 0


    private lateinit var mProductAdapter: ProductAdapter
    private var mProductList = ArrayList<Product>()

    private var mVariantsList = ArrayList<Variant>()
    private lateinit var mVariantAdapter: VariantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleExtras()
        productDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        productDetailBinding.productDetailViewModel = productDetailViewModel
        productDetailBinding.lifecycleOwner = this
        setProductAdapter()
        setVariantAdapter()
        setProductList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.menuByOrder -> {
                filter(COLUMN_ORDER_COUNT)
                true
            }

            R.id.menuByProduct -> {
                filter(COLUMN_VIEW_COUNT)
                true
            }

            R.id.menuShare->{
                filter(COLUMN_SHARES)
                true
            }

            R.id.menuReset->{
                setProductList()
                true
            }


            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun setVariantAdapter() {
        mVariantAdapter = VariantAdapter(mVariantsList)
        rvVariant.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvVariant.adapter = mVariantAdapter
    }

    private fun setProductList() {
        mProductList.clear()
        mVariantsList.clear()
        mProductList.addAll(productDetailViewModel.getProductListFromDB(mCategoryId))
        mProductAdapter.notifyDataSetChanged()
    }

    private fun getBundleExtras() {
        if (intent.hasExtra(EXTRA_CATEGORY_ID)) {
            mCategoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0)
        }
    }

    private fun setProductAdapter() {
        mProductAdapter = ProductAdapter(mProductList) { position ->
            val productId = mProductList[position].id!!
            mVariantsList.clear()
            mVariantsList.addAll(productDetailViewModel.getProductVariantFromDB(productId))
            mVariantAdapter.notifyDataSetChanged()
            mVariantAdapter.setProductName(mProductList[position].productName!!)
            productDetailViewModel.isToShowVariant.value = mVariantsList.isNotEmpty()
        }
        rvProduct.layoutManager = LinearLayoutManager(this)
        rvProduct.adapter = mProductAdapter
    }

    private fun filter(filterType: String){
        mProductList.clear()
        mVariantsList.clear()

        mVariantAdapter.notifyDataSetChanged()

        mProductList.addAll(productDetailViewModel.getProductByFilter(filterType, mCategoryId))
        mProductAdapter.notifyDataSetChanged()

    }
}
