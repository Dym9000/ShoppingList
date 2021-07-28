package com.example.shoppinglist.scenes.shopping_lists.list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.scenes.shopping_lists.list_details.repository.ProductRepositoryImpl

class ShoppingDetailsViewModelFactory(
    private val repository: ProductRepositoryImpl,
    private val itemId: Long,
    private val isArchived: Int
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingDetailsViewModel::class.java)) {
            return ShoppingDetailsViewModel(repository, itemId, isArchived) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}