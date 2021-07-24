package com.example.shoppinglist.persistence.shopping_list.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,

    @NonNull
    var name: String = "Some Grocery",

    @NonNull
    var amount: String = "2500 g",

    @NonNull
    var isInTheCart: Int = 0

)
