package com.example.shoppinglist.scenes.shopping_lists.list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.scenes.shopping_lists.list_details.repository.ProductRepository
import kotlinx.coroutines.launch

class ShoppingDetailsViewModel(
    private val repository: ProductRepository,
    private val shoppingListId: Long,
    private val isArchived: Int
) : ViewModel() {

    val products = repository.getProducts(shoppingListId)

    fun onClick(productId: Int) {
        if (isArchived == 0) {
            viewModelScope.launch {
                if (repository.isProductPicked(productId) == UNCHECK) {
                        repository.updateProductCheck(productId, CHECK)
                    } else {
                        repository.updateProductCheck(productId, UNCHECK)
                    }
            }
        }
    }

    fun onFabClick(name: String?, amount: String?) {
        if (isArchived != 1) {
            viewModelScope.launch {
                    repository.addProduct(shoppingListId, name, amount)
            }
        }
    }

    fun onSwiped(id: Int) {
        viewModelScope.launch {
                repository.removeProduct(id)
        }
    }

    companion object {
        private const val CHECK = 1
        private const val UNCHECK = 0
    }
}