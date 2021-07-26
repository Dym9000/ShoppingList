package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListDetailsBinding
import com.example.shoppinglist.scenes.shopping_lists.common.ItemTouchHelperHandler
import com.example.shoppinglist.scenes.shopping_lists.common.interfaces.CustomItemTouchHelper
import com.example.shoppinglist.scenes.shopping_lists.common.interfaces.OnSceneChange
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListDetailsFragment : Fragment(), CustomItemTouchHelper {

    @Inject
    lateinit var repository: ProductRepository
    private val detailsArgs: ShoppingListDetailsFragmentArgs by navArgs()
    private val detailsViewModel: ShoppingDetailsViewModel by viewModels(){
        ShoppingDetailsViewModelFactory(repository, detailsArgs.shoppingListId, detailsArgs.isArchived)
    }

    private lateinit var detailsBinding: FragmentShoppingListDetailsBinding
    private lateinit var detailsAdapter: ShoppingDetailsAdapter

    private lateinit var onSceneChangedListener: OnSceneChange

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSceneChangedListener = context as OnSceneChange
    }

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
        setItemTouchHelper()

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

    private fun setItemTouchHelper(){
        val swipeHandler = ItemTouchHelperHandler(this)
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(detailsBinding.recView.recViewShoppingList)
    }

    override fun onSwiped(position: Int){
        val id = detailsAdapter.getItemIdAtPosition(position)
        detailsViewModel.onSwiped(id)
        Toast.makeText(activity, "Removed from the list", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        onSceneChangedListener.apply{
            setToolbarExpanded(true)
            setToolbarTitle(getString(R.string.shopping_details_toolbar_title))
        }
    }

    override fun onDestroyView() {
        detailsBinding.recView.recViewShoppingList.adapter = null
        super.onDestroyView()
    }
}