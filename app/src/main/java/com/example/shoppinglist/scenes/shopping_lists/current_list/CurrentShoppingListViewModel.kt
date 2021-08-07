package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.scenes.shopping_lists.common.repository.ShoppingListRepository
import com.example.shoppinglist.scenes.shopping_lists.current_list.utils.ConstantsCurrentList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrentShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {

    val currentShoppingList = repository.getCurrentShoppingList()

    private val _shoppingListId = MutableLiveData<Bundle>()
    val shoppingListId: LiveData<Bundle>
        get() {
            return _shoppingListId
        }

    fun onClick(id: Long, isArchived: Int) {
        _shoppingListId.value = bundleOf(
            Pair(ConstantsCurrentList.BUNDLE_ARGS_1, id),
            Pair(ConstantsCurrentList.BUNDLE_ARGS_2, isArchived)
        )
    }

    fun onNavigated() {
        _shoppingListId.value = bundleOf(Pair(ConstantsCurrentList.BUNDLE_ARGS_1, -1))
    }

    fun onFabClick(newName: String?, shoppingDate: Calendar?) {
        viewModelScope.launch {
                repository.addShoppingList(newName, shoppingDate)
        }
    }

    fun onSwiped(id: Int) {
        viewModelScope.launch {
                repository.moveToArchive(id.toLong())
        }
    }

}