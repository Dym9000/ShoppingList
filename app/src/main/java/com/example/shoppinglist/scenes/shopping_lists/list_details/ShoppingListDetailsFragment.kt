package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListDetailsFragment : Fragment() {

    private val detailsViewModel: ShoppingDetailsViewModel by viewModels()
    private lateinit var detailsBinding: FragmentShoppingListDetailsBinding
    private lateinit var detailsAdapter: ShoppingDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        detailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shopping_list_details, container, false)

        detailsBinding.apply {
            lifecycleOwner = this@ShoppingListDetailsFragment.viewLifecycleOwner
            viewModel = detailsViewModel
        }

        setRecyclerView()
        setObservers()

        return detailsBinding.root
    }

    private fun setRecyclerView(){
        detailsAdapter = ShoppingDetailsAdapter(
            OnProductListClickListener { productId ->
                detailsViewModel.onClick(productId)
            })
        detailsBinding.recView.recViewShoppingList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = detailsAdapter
        }
    }

    private fun setObservers(){
        detailsViewModel.products.observe(viewLifecycleOwner,{
            it?.let{
                detailsAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        detailsBinding.recView.recViewShoppingList.adapter = null
        super.onDestroyView()
    }
}