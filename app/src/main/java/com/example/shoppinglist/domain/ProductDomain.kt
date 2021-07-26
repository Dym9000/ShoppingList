package com.example.shoppinglist.domain

data class ProductDomain(
    val id: Int,
    val shoppingListId: Long,
    val name: String,
    val amount: String,
    val isInTheCart: Int
)
