package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.DetailsShoppingListItemBinding
import com.example.shoppinglist.domain.ProductDomain

class ShoppingDetailsAdapter(
    private val onProductListClickListener: OnProductListClickListener
)
    : ListAdapter<ProductDomain, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    fun getItemIdAtPosition(position: Int): Int{
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DetailsShoppingListItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding, onProductListClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShoppingDetailsAdapter.ProductViewHolder).bind(currentList[position])
    }

    inner class ProductViewHolder(
        private val binding: DetailsShoppingListItemBinding,
        private val onProductListClickListener: OnProductListClickListener
    )
        : RecyclerView.ViewHolder(binding.root){

            fun bind(product: ProductDomain){
                binding.productName.text = product.name
                binding.productAmount.text = product.amount
                binding.onProductClickListener = onProductListClickListener
                binding.domainModel = product
                binding.imageView2.setItemCheck(product.isInTheCart)
                binding.executePendingBindings()
            }
    }

}

class ProductDiffCallback(): DiffUtil.ItemCallback<ProductDomain>(){

    override fun areItemsTheSame(oldItem: ProductDomain, newItem: ProductDomain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductDomain, newItem: ProductDomain): Boolean {
        return (oldItem.name == newItem.name && oldItem.amount == newItem.amount
                && oldItem.isInTheCart == newItem.isInTheCart)
    }
}

class OnProductListClickListener(val clickListener: (id: Int) -> Unit){
    fun onClick(product: ProductDomain) = clickListener(product.id)
}