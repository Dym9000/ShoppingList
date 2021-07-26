package com.example.shoppinglist.scenes.shopping_lists.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ShoppingListItemBinding
import com.example.shoppinglist.domain.ShoppingListDomain
import com.example.shoppinglist.util.DateFormatter

class ShoppingListAdapter(
    private val onShoppingListClickListener: OnShoppingListClickListener
)
    : ListAdapter<ShoppingListDomain, RecyclerView.ViewHolder>(ShoppingDiffCallback()) {

    fun getItemIdAtPosition(position: Int): Int{
        return getItem(position).id.toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShoppingListItemBinding.inflate(inflater, parent, false)
        return ShoppingListViewHolder(binding, onShoppingListClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShoppingListViewHolder).bind(currentList[position])
    }

    inner class ShoppingListViewHolder(
        private val binding: ShoppingListItemBinding,
        private val shoppingListListener: OnShoppingListClickListener
    )
        : RecyclerView.ViewHolder(binding.root){

            fun bind(shoppingList: ShoppingListDomain){
                binding.shoppingListName.text = shoppingList.name
                binding.shoppingDate.text = DateFormatter.formatDate(shoppingList.shoppingDate.time)
                binding.domainModel = shoppingList
                binding.onShoppingListClickListener = shoppingListListener
                binding.executePendingBindings()
            }
    }

}

class ShoppingDiffCallback(): DiffUtil.ItemCallback<ShoppingListDomain>(){

    override fun areItemsTheSame(
        oldItem: ShoppingListDomain,
        newItem: ShoppingListDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShoppingListDomain,
        newItem: ShoppingListDomain
    ): Boolean {
        return (oldItem.name == newItem.name && oldItem.shoppingDate == newItem.shoppingDate)
    }
}

class OnShoppingListClickListener(val clickListener: (id: Long, isArchived: Int) -> Unit){
    fun onClick(shoppingList: ShoppingListDomain) = clickListener(shoppingList.id, shoppingList.isArchived)
}