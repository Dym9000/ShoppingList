package com.example.shoppinglist.scenes.shopping_lists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.common.interfaces.OnSceneChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListActivity : AppCompatActivity(), OnSceneChange {

    private lateinit var mainBinding: ActivityShoppingListBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)
        
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_shopping_list) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(mainBinding.toolbarShoppingList)
        setupActionBarWithNavController(navController)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun setToolbarTitle(title: String) {
        mainBinding.toolbarTitleShoppingList.text = title
    }

    override fun setToolbarExpanded(isExpanded: Boolean) {
        mainBinding.appBarShoppingList.setExpanded(isExpanded)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}