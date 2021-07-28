package com.example.shoppinglist.scenes.shopping_lists.common.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.domain.ShoppingListDomain
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.persistence.shopping_list.mapper.ShoppingListEntityMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListRepositoryImpl @Inject constructor(
    private val dao: ShoppingListDao,
    private val mapper: ShoppingListEntityMapper
) : ShoppingListRepository {

    override fun getCurrentShoppingList(): LiveData<List<ShoppingListDomain>> {
        return dao.getCurrentShoppingLists().map {
            mapToDomain(it)
        }
    }

    override fun getArchivedShoppingList(): LiveData<List<ShoppingListDomain>> {
        return dao.getArchivedShoppingLists().map {
            mapToDomain(it)
        }
    }

    private fun mapToDomain(list: List<ShoppingListEntity>): List<ShoppingListDomain> {
        return mapper.mapFromList(list)
    }

    override suspend fun addShoppingList(name: String?, date: Calendar?) {
        val newShoppingList = ShoppingListEntity(
            name = if (name.isNullOrEmpty()) "Default list" else name,
            shoppingDate = date ?: Calendar.getInstance()
        )
        dao.addShoppingList(newShoppingList)
    }

    override suspend fun removeShoppingList(id: Long) {
        dao.removeShoppingList(id)
    }

    override suspend fun moveToArchive(id: Long) {
        dao.archiveShoppingList(id)
    }

}