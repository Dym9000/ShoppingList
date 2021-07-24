package com.example.shoppinglist.persistence.shopping_list.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity

@Dao
interface ShoppingListDao {

    @Insert
    suspend fun addShoppingList(shoppingList: ShoppingListEntity)

    @Query("Delete from shopping_list where id = :id")
    suspend fun removeShoppingList(id: Int)

    @Query("Update shopping_list Set isArchived = 1 where id = :id")
    suspend fun archiveShoppingList (id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM shopping_list WHERE id = :id)")
    suspend fun isShoppingListArchived(id: Int)

    @Query("Select * from shopping_list where isArchived = 0")
    fun getCurrentShoppingLists(): LiveData<List<ShoppingListEntity>>

    @Query("Select * from shopping_list where isArchived = 1")
    fun getArchivedShoppingLists(): LiveData<List<ShoppingListEntity>>

}