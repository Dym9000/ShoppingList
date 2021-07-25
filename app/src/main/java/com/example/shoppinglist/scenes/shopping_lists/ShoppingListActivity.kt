package com.example.shoppinglist.scenes.shopping_lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists.util.interfaces.OnSceneChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListActivity : AppCompatActivity(), OnSceneChange {

    private lateinit var mainBinding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)

        setupActionBar()
    }

    private fun setupActionBar(){
        setSupportActionBar(mainBinding.toolbarShoppingList)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun setToolbarTitle(title: String) {
        mainBinding.toolbarTitleShoppingList.text = title
    }

}