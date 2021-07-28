package com.example.shoppinglist.scenes.shopping_lists.list_details.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.domain.ProductDomain
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.persistence.shopping_list.mapper.ProductEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingListDao,
    private val productMapper: ProductEntityMapper
) : ProductRepository {

    override fun getProducts(id: Long): LiveData<List<ProductDomain>> {
        val products = shoppingDao.getAllProducts(id)
        return products.map {
            mapToDomain(it.products)
        }
    }

    private fun mapToDomain(list: List<ProductEntity>): List<ProductDomain> {
        return productMapper.mapFromList(list)
    }

    override suspend fun removeProduct(id: Int) {
        shoppingDao.removeProduct(id)
    }

    override suspend fun addProduct(id: Long, name: String?, amount: String?) {
        val newProduct = ProductEntity(
            shoppingListId = id,
            name = if (name.isNullOrEmpty()) "Default product" else name,
            amount = amount ?: ""
        )
        shoppingDao.addProduct(newProduct)
    }

    override suspend fun isProductPicked(productId: Int): Int {
        return shoppingDao.isProductPicked(productId)
    }

    override suspend fun updateProductCheck(id: Int, check: Int) {
        shoppingDao.updateProductCheck(id, check)
    }

}