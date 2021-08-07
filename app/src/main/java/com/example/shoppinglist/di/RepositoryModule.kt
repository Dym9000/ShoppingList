package com.example.shoppinglist.di

import com.example.shoppinglist.scenes.shopping_lists.common.repository.ShoppingListRepository
import com.example.shoppinglist.scenes.shopping_lists.common.repository.ShoppingListRepositoryImpl
import com.example.shoppinglist.scenes.shopping_lists.list_details.repository.ProductRepository
import com.example.shoppinglist.scenes.shopping_lists.list_details.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun bindShoppingListRepository(repository: ShoppingListRepositoryImpl): ShoppingListRepository
}