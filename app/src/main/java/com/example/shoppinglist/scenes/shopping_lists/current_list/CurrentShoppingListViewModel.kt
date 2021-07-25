package com.example.shoppinglist.scenes.shopping_lists.current_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.scenes.shopping_lists.common.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrentShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
): ViewModel() {

    val currentShoppingList = repository.getCurrentShoppingList()

    fun onClick(id: Long){

    }

    fun onFabClick(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.addShoppingList()
            }
        }
    }

}