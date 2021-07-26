package com.example.shoppinglist.scenes.shopping_lists.list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingDetailsViewModel(
    private val repository: ProductRepository,
    private val shoppingListId: Long,
    private val isArchived: Int
): ViewModel() {

    val products = repository.getProducts(shoppingListId)

    fun onClick(productId: Int){
        if(isArchived == 0) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (repository.isProductPicked(productId) == 1) {
                        repository.unmarkProduct(productId)
                    } else {
                        repository.markProduct(productId)
                    }
                }
            }
        }
    }

    fun onFabClick(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.addProduct(shoppingListId)
            }
        }
    }

    fun onSwiped(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.removeProduct(id)
            }
        }
    }

}