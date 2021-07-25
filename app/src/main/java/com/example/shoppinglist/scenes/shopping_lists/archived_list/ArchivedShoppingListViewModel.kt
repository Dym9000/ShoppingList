package com.example.shoppinglist.scenes.shopping_lists.archived_list

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.scenes.shopping_lists.common.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArchivedShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
): ViewModel() {

    val archivedShoppingList = repository.getArchivedShoppingList()

    fun onClick(id: Long){

    }

}