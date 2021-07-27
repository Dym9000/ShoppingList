package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentCurrentShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.common.ItemTopBottomSpacing
import com.example.shoppinglist.scenes.shopping_lists.common.ItemTouchHelperHandler
import com.example.shoppinglist.scenes.shopping_lists.common.OnShoppingListClickListener
import com.example.shoppinglist.scenes.shopping_lists.common.ShoppingListAdapter
import com.example.shoppinglist.scenes.shopping_lists.common.interfaces.CustomItemTouchHelper
import com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists.ShoppingListViewPagerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentShoppingListFragment : Fragment(), CustomItemTouchHelper {

    private val currentViewModel: CurrentShoppingListViewModel by viewModels()
    private lateinit var currentBinding: FragmentCurrentShoppingListBinding
    private lateinit var currentAdapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        currentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_current_shopping_list, container, false
        )

        currentBinding.apply {
            lifecycleOwner = this@CurrentShoppingListFragment.viewLifecycleOwner
            viewModel = currentViewModel
        }

        setRecyclerView()
        setObservers()
        setItemTouchHelper()

        return currentBinding.root
    }

    private fun setRecyclerView() {
        val itemTopBottomSpacing = ItemTopBottomSpacing(3)
        currentAdapter = ShoppingListAdapter(
            OnShoppingListClickListener { listId, isArchived ->
                currentViewModel.onClick(listId, isArchived)
            })
        currentBinding.recView.recViewShoppingList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = currentAdapter
            addItemDecoration(itemTopBottomSpacing)
        }
    }

    private fun setObservers() {
        currentViewModel.currentShoppingList.observe(viewLifecycleOwner, {
            it?.let {
                currentAdapter.submitList(it)
            }
        })

        currentViewModel.shoppingListId.observe(viewLifecycleOwner, {
            if (it["1"] != -1) {
                this.findNavController().navigate(
                    ShoppingListViewPagerFragmentDirections
                        .actionShoppingListViewPagerFragmentToShoppingListDetailsFragment(
                            it.getLong("1"), it.getInt("2")
                        )
                )
                currentViewModel.onNavigated()
            }
        })

    }

    private fun setItemTouchHelper() {
        val swipeHandler = ItemTouchHelperHandler(this)
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(currentBinding.recView.recViewShoppingList)
    }

    override fun onSwiped(position: Int) {
        val id = currentAdapter.getItemIdAtPosition(position)
        currentViewModel.onSwiped(id)
        Toast.makeText(activity, "Moved to archive", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        currentBinding.recView.recViewShoppingList.adapter = null
        super.onDestroyView()
    }

}