package com.example.shoppinglist.persistence.shopping_list.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingListWithProducts(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopping_list_id"
    )
    val products: List<ProductEntity>
)
