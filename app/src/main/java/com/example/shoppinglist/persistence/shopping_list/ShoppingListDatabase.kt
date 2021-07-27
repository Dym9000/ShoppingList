package com.example.shoppinglist.persistence.shopping_list

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.persistence.shopping_list.util.CalendarConverter

@Database(
    entities = [
        ProductEntity::class,
        ShoppingListEntity::class
    ], version = 2, exportSchema = false
)
@TypeConverters(CalendarConverter::class)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract val shoppingListDao: ShoppingListDao

    companion object {
        const val DATABASE_NAME: String = "shopping_list_database"
    }

}