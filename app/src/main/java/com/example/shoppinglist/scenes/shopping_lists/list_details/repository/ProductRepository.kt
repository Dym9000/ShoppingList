package com.example.shoppinglist.scenes.shopping_lists.list_details.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ProductDomain

interface ProductRepository {

    fun getProducts(id: Long): LiveData<List<ProductDomain>>

    suspend fun removeProduct(id: Int)

    suspend fun addProduct(id: Long)

    suspend fun isProductPicked(productId: Int): Int

    suspend fun updateProductCheck(id: Int, check:Int)

}