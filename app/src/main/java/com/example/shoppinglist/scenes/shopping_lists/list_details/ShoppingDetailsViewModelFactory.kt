package com.example.shoppinglist.scenes.shopping_lists.list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingDetailsViewModelFactory(
    private val repository: ProductRepository,
    private val itemId: Long,
    private val isCurrent: Int
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingDetailsViewModel::class.java)
            && itemId != null && isCurrent != null
        ) {
            return ShoppingDetailsViewModel(repository, itemId, isCurrent) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}