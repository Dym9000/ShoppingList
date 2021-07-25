package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentCurrentShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.common.OnShoppingListClickListener
import com.example.shoppinglist.scenes.shopping_lists.common.ShoppingListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentShoppingListFragment : Fragment() {

    private val currentViewModel: CurrentShoppingListViewModel by viewModels()
    private lateinit var currentBinding: FragmentCurrentShoppingListBinding
    private lateinit var currentAdapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        currentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_current_shopping_list, container, false)

        currentBinding.apply {
            lifecycleOwner = this@CurrentShoppingListFragment.viewLifecycleOwner
            viewModel = currentViewModel
        }

            setRecyclerView()
            setObservers()

            return currentBinding.root
    }

    private fun setRecyclerView(){
        currentAdapter = ShoppingListAdapter(
            OnShoppingListClickListener { listId ->
                currentViewModel.onClick(listId)
            })
        currentBinding.recView.recViewShoppingList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = currentAdapter
        }
    }

    private fun setObservers(){
        currentViewModel.currentShoppingList.observe(viewLifecycleOwner,{
            it?.let{
                currentAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        currentBinding.recView.recViewShoppingList.adapter = null
        super.onDestroyView()
    }

}