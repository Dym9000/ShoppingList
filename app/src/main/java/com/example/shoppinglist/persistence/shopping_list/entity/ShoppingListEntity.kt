package com.example.shoppinglist.persistence.shopping_list.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shopping_list")
data class ShoppingListEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Long = 0L,

    @NonNull
    var name: String = "Shopping List",

    @NonNull
    @ColumnInfo(name = "shopping_date")
    var shoppingDate: Calendar = Calendar.getInstance(),

    @NonNull
    var isArchived: Int = 0

)
