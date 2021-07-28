package com.example.shoppinglist.scenes.shopping_lists.common.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShoppingListDomain
import java.util.*

interface ShoppingListRepository {

    fun getCurrentShoppingList(): LiveData<List<ShoppingListDomain>>

    fun getArchivedShoppingList(): LiveData<List<ShoppingListDomain>>

    suspend fun addShoppingList(name: String?, date: Calendar?)

    suspend fun removeShoppingList(id: Long)

    suspend fun moveToArchive(id: Long)
}