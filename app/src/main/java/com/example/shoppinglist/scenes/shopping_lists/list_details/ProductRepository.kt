package com.example.shoppinglist.scenes.shopping_lists.list_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.domain.ProductDomain
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.persistence.shopping_list.mapper.ProductEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val shoppingDao: ShoppingListDao,
    private val productMapper: ProductEntityMapper
){

    fun getProducts(id: Long): LiveData<List<ProductDomain>>{
        val products = shoppingDao.getAllProducts(id)
        return products.map {
            mapToDomain(it.products)
        }
    }

    private fun mapToDomain(list: List<ProductEntity>): List<ProductDomain>{
        return productMapper.mapFromList(list)
    }

    suspend fun addProduct(id: Long){
        val newProduct = ProductEntity(shoppingListId = id)
        shoppingDao.addProduct(newProduct)
    }

    suspend fun isProductPicked(productId: Int): Int{
        return shoppingDao.isProductPicked(productId)
    }

    suspend fun markProduct(id: Int){
        shoppingDao.markProductAsPicked(id)
    }

    suspend fun unmarkProduct(id: Int){
        shoppingDao.markProductAsNotPicked(id)
    }

}