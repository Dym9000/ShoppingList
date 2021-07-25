package com.example.shoppinglist.persistence.shopping_list.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert
    suspend fun addProduct(product: ProductEntity)

    @Query("Delete from product where id = :id")
    suspend fun removeProduct(id: Int)

    @Query("Select * from product")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    @Query("Update product Set isInTheCart = 1 where id = :id")
    suspend fun markProductAsPicked(id: Int)

    @Query("Update product Set isInTheCart = 0 where id = :id")
    suspend fun markProductAsNotPicked(id: Int)

}