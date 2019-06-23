package com.demo.heady.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.demo.heady.model.*
import org.w3c.dom.ls.LSInput

@Dao
abstract class AppDao() {


    @Insert
    abstract fun insertCategory(categories: Categories)

    @Insert
    abstract fun insertAllCategories(categories: List<Categories>)

    @Query("select * from Categories limit 1")
    abstract fun getOneCategory(): Categories?

    fun isNotEmpty(): Boolean {
        return getOneCategory() != null
    }

    @Query("update Categories set parentId=:parentId where id=:CatId")
    abstract fun updateParentId(CatId: Int, parentId: Int)

    @Transaction
    open fun fillAllTablesData(it: ResponseModel) {
        if (it.categories != null) {
            insertAllCategories(it.categories!!)

            val catCount = HashMap<Int, Int>()
            it.categories!!.forEachIndexed { index1, categories ->

                categories.childCategories?.forEachIndexed { indexChildCat, catId ->
                    if (categories.id != null) {
                        updateParentId(catId, categories.id!!)
                    }
                }

                val productList = categories.products!!

                if (productList.isNotEmpty()) {

                    productList.forEachIndexed { index2, product ->

                        val product: Product = categories.products!!.get(index2)

                        if (product != null) {

                            product.fk_categotyId = categories.id!!
                            insertProduct(product)

                            val variantList = categories.products!!.get(index2).variants!!

                            if (variantList.isNotEmpty()) {

                                variantList.forEachIndexed { index3, variant ->

                                    val variant = categories.products!!.get(index2).variants!!.get(index3)
                                    if (variant != null) {
                                        variant.fkProductId = product.id
                                        insertProductVariant(variant)
                                    }
                                }
                            }

                        }

                    }
                }
            }

            insertRanking(it.rankings!!)


        }
    }


    @Transaction
    open fun updateProduct(it: ResponseModel) {

        it.rankings!!.forEachIndexed { index1, ranking ->
            val productRankingList = ranking.productsRanking!!
            productRankingList.forEachIndexed { index2, productRanking ->
                if (productRanking.viewCount != null) {
                    updateMostViews(productRanking.viewCount, productRankingList[index2].id!!)
                } else if (productRanking.shares != null) {
                    updateShare(productRanking.shares, productRankingList[index2].id!!)
                } else if (productRanking.orderCount != null) {
                    updateOrderCount(productRanking.orderCount, productRankingList[index2].id!!)
                }
            }
        }
    }

    @Insert
    abstract fun insertProduct(product: Product)

    @Insert
    abstract fun insertAllProduct(product: List<Product>)

    @Insert
    abstract fun insertRanking(ranking: List<Ranking>)

    @Insert
    abstract fun insertProductVariant(variant: Variant)

    @Query("select * from Categories where parentId =:mparentId")
    abstract fun getCategoryList(mparentId: Int = 0): List<Categories>

    @Query("select * from Product where fk_categotyId =:mCategoryId")
    abstract fun getProductList(mCategoryId: Int = 0): List<Product>

    @Query("select * from Variant where fk_productId=:mProductId")
    abstract fun getVariantsList(mProductId: Int = 0): List<Variant>

    @Query("update Product set order_count=:orderCount where id=:productId")
    abstract fun updateOrderCount(orderCount: Int, productId: Int)

    @Query("update Product set shares=:sharesCount where id=:productId")
    abstract fun updateShare(sharesCount: Int, productId: Int)

    @Query("update Product set view_count=:viewsCount where id=:productId")
    abstract fun updateMostViews(viewsCount: Int, productId: Int)

    @Query("select * from Product")
    abstract fun getAllProduct(): List<Product>

    @Query("select * from Product where id=:categoryId ORDER BY :field ASC ")
    abstract fun sortByFields(field: String,categoryId : Int =0) : List<Product>

}