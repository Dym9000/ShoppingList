package com.example.shoppinglist.persistence.shopping_list.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Long,

    @NonNull
    var name: String = "Some Grocery",

    @NonNull
    var amount: String = "2500 g",

    @NonNull
    @ColumnInfo(name= "is_in_the_cart")
    var isInTheCart: Int = 0

)
