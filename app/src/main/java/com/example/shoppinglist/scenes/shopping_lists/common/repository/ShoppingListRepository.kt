package com.example.shoppinglist.scenes.shopping_lists.common.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShoppingListDomain

interface ShoppingListRepository {

    fun getCurrentShoppingList(): LiveData<List<ShoppingListDomain>>

    fun getArchivedShoppingList(): LiveData<List<ShoppingListDomain>>

    suspend fun addShoppingList()

    suspend fun removeShoppingList(id: Long)

    suspend fun moveToArchive(id: Long)
}