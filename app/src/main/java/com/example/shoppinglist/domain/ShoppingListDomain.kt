package com.example.shoppinglist.domain

import java.util.*

data class ShoppingListDomain(
    val id: Long,
    val name: String,
    val shoppingDate: Calendar = Calendar.getInstance(),
    val isArchived: Int
)
