package com.example.shoppinglist.scenes.shopping_lists.archived_list

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.scenes.shopping_lists.archived_list.utils.ConstantsArchived
import com.example.shoppinglist.scenes.shopping_lists.common.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivedShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {

    val archivedShoppingList = repository.getArchivedShoppingList()

    private val _shoppingListId = MutableLiveData<Bundle>()
    val shoppingListId: LiveData<Bundle>
        get() {
            return _shoppingListId
        }

    fun onClick(id: Long, isArchived: Int) {
        _shoppingListId.value = bundleOf(
            Pair(ConstantsArchived.BUNDLE_ARGS_1, id),
            Pair(ConstantsArchived.BUNDLE_ARGS_2, isArchived)
        )
    }

    fun onNavigated() {
        _shoppingListId.value = bundleOf(Pair(ConstantsArchived.BUNDLE_ARGS_1, -1))
    }

    fun onSwiped(id: Int) {
        viewModelScope.launch {
                repository.removeShoppingList(id.toLong())
        }
    }

}