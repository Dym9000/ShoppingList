package com.example.shoppinglist.scenes.shopping_lists.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.domain.ShoppingListDomain
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.persistence.shopping_list.mapper.ShoppingListEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListRepository @Inject constructor(
    private val dao: ShoppingListDao,
    private val mapper: ShoppingListEntityMapper
) {

    fun getCurrentShoppingList(): LiveData<List<ShoppingListDomain>> {
        return dao.getCurrentShoppingLists().map {
            mapToDomain(it)
        }
    }

    fun getArchivedShoppingList(): LiveData<List<ShoppingListDomain>> {
        return dao.getArchivedShoppingLists().map {
            mapToDomain(it)
        }
    }

    private fun mapToDomain(list: List<ShoppingListEntity>): List<ShoppingListDomain>{
        return mapper.mapFromList(list)
    }

    suspend fun addShoppingList(){
        val newShoppingList = ShoppingListEntity()
        dao.addShoppingList(newShoppingList)
    }

    suspend fun removeShoppingList(id: Long){
        dao.removeShoppingList(id)
    }

    suspend fun moveToArchive(id: Long){
        dao.archiveShoppingList(id)
    }

}