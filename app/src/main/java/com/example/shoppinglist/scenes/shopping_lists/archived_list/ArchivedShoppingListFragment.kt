package com.example.shoppinglist.scenes.shopping_lists.archived_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentArchivedShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.common.OnShoppingListClickListener
import com.example.shoppinglist.scenes.shopping_lists.common.ShoppingListAdapter
import com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists.ShoppingListViewPagerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchivedShoppingListFragment : Fragment() {

    private val archivedViewModel: ArchivedShoppingListViewModel by viewModels()
    private lateinit var archivedBinding: FragmentArchivedShoppingListBinding
    private lateinit var archivedAdapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        archivedBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_archived_shopping_list, container, false)

        archivedBinding.apply {
            lifecycleOwner = this@ArchivedShoppingListFragment.viewLifecycleOwner
            viewModel = archivedViewModel
        }

        setRecyclerView()
        setObservers()

        return archivedBinding.root
    }

    private fun setRecyclerView(){
        archivedAdapter = ShoppingListAdapter(
            OnShoppingListClickListener { listId, isArchived ->
                archivedViewModel.onClick(listId, isArchived)
            })
        archivedBinding.recViewArchived.recViewShoppingList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = archivedAdapter
        }
    }

    private fun setObservers(){
        archivedViewModel.archivedShoppingList.observe(viewLifecycleOwner,{
            it?.let{
                archivedAdapter.submitList(it)
            }
        })

        archivedViewModel.shoppingListId.observe(viewLifecycleOwner, {
            if (it["1"] != -1) {
                this.findNavController().navigate(
                    ShoppingListViewPagerFragmentDirections
                        .actionShoppingListViewPagerFragmentToShoppingListDetailsFragment(
                            it.getLong("1"), it.getInt("2")
                        )
                )
                archivedViewModel.onNavigated()
            }
        })
    }

    override fun onDestroyView() {
        archivedBinding.recViewArchived.recViewShoppingList.adapter = null
        super.onDestroyView()
    }
}