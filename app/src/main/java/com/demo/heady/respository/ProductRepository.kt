package com.demo.heady.respository

import android.app.Application
import com.demo.heady.db.AppDatabase
import com.demo.heady.model.Product
import com.demo.heady.utils.COLUMN_ORDER_COUNT
import com.demo.heady.utils.COLUMN_SHARES
import com.demo.heady.utils.COLUMN_VIEW_COUNT

class ProductRepository(app: Application) {

    val dao = AppDatabase.getDatabase(app).appDao()

    fun getProductListFromDB(categoryId: Int = 0) = dao.getProductList(categoryId)

    fun getVariantsListFromDB(productId: Int = 0) = dao.getVariantsList(productId)

    fun getFilterListFromDB(filterType: String, categoryId: Int = 0): List<Product> {
        when (filterType) {
            COLUMN_ORDER_COUNT -> return dao.sortByOrderCount(categoryId)
            COLUMN_SHARES -> return dao.sortByShare(categoryId)
            COLUMN_VIEW_COUNT -> return dao.sortByViewCount(categoryId)
        }
        return emptyList()
    }
}