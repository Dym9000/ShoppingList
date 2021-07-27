package com.example.shoppinglist.persistence.shopping_list.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListWithProducts

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShoppingList(shoppingList: ShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductEntity)


    @Query("Delete from shopping_list where id = :id")
    suspend fun removeShoppingList(id: Long)

    @Query("Delete from product where id = :id")
    suspend fun removeProduct(id: Int)


    @Query("Update shopping_list Set isArchived = 1 where id = :id")
    suspend fun archiveShoppingList(id: Long)

    @Query("Update product Set is_in_the_cart = :check where id = :id")
    suspend fun updateProductCheck(id: Int, check: Int)


    @Query("SELECT EXISTS(SELECT 1 FROM product WHERE id = :id and is_in_the_cart = 1)")
    suspend fun isProductPicked(id: Int): Int


    @Query("Select * from shopping_list where isArchived = 0 Order by shopping_date DESC")
    fun getCurrentShoppingLists(): LiveData<List<ShoppingListEntity>>

    @Query("Select * from shopping_list where isArchived = 1 Order by shopping_date DESC")
    fun getArchivedShoppingLists(): LiveData<List<ShoppingListEntity>>

    @Transaction
    @Query("Select * from shopping_list where id = :listId")
    fun getAllProducts(listId: Long): LiveData<ShoppingListWithProducts>

}