package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
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
import java.util.*

@AndroidEntryPoint
class CurrentShoppingListFragment : Fragment(), CustomItemTouchHelper, FragmentResultListener {

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

        childFragmentManager.setFragmentResultListener("name", viewLifecycleOwner, this)

        setRecyclerView()
        setObservers()
        setItemTouchHelper()
        setOnFabClickListener()

        return currentBinding.root
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
            val name = result.getString("name")
            currentViewModel.onFabClick(name!!, null)
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
            if (it[Constants.BUNDLE_KEY_1] != -1) {
                this.findNavController().navigate(
                    ShoppingListViewPagerFragmentDirections
                        .actionShoppingListViewPagerFragmentToShoppingListDetailsFragment(
                            it.getLong(Constants.BUNDLE_KEY_1), it.getInt(Constants.BUNDLE_KEY_2)
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

    private fun setOnFabClickListener(){
        currentBinding.fabShoppingList.setOnClickListener{view ->
            openDialog()
        }
    }

    private fun openDialog() {
        val newShoppingListDialog = DialogNewShoppingList()
        newShoppingListDialog.show(childFragmentManager, "New Shopping List Dialog")
    }

    override fun onDestroyView() {
        currentBinding.recView.recViewShoppingList.adapter = null
        super.onDestroyView()
    }

}