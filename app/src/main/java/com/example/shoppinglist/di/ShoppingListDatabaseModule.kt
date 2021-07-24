package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglist.persistence.shopping_list.ShoppingListDatabase
import com.example.shoppinglist.persistence.shopping_list.dao.ProductDao
import com.example.shoppinglist.persistence.shopping_list.dao.ShoppingListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingListDatabaseModule {

    @Singleton
    @Provides
    fun provideShoppingListDatabase (@ApplicationContext context: Context): ShoppingListDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingListDatabase::class.java,
            ShoppingListDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideShoppingListDao(shoppingListDatabase: ShoppingListDatabase): ShoppingListDao {
        return shoppingListDatabase.shoppingListDao
    }

    @Singleton
    @Provides
    fun provideProductDao(shoppingListDatabase: ShoppingListDatabase): ProductDao {
        return shoppingListDatabase.productDao
    }
}