package com.example.shoppinglist.persistence.shopping_list.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.shoppinglist.persistence.shopping_list.ShoppingListDatabase
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.persistence.shopping_list.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class ProductDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var shoppingDatabase: ShoppingListDatabase
    private lateinit var shoppingDao: ShoppingListDao

    @Before
    fun setup(){
        shoppingDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingListDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingDao = shoppingDatabase.shoppingListDao
    }

    @After
    fun teardown(){
        shoppingDatabase.close()
    }

    @Test
    fun addShoppingList(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)

            val lists = shoppingDao.getCurrentShoppingLists().getOrAwaitValue()
            assertThat(lists).contains(shoppingList1)
        }
    }

    @Test
    fun addProduct(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            val product1 = ProductEntity(id = 2, shoppingListId = 1L)
            shoppingDao.addProduct(product1)

            val products = shoppingDao.getAllProducts(1L).getOrAwaitValue()
            assertThat(products.products).contains(product1)
        }
    }

    @Test
    fun removeShoppingList(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            shoppingDao.removeShoppingList(1L)

            val lists = shoppingDao.getCurrentShoppingLists().getOrAwaitValue()
            assertThat(lists).doesNotContain(shoppingList1)
        }
    }

    @Test
    fun removeProduct(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            val product1 = ProductEntity(id = 2, shoppingListId = 1L)
            shoppingDao.addProduct(product1)
            shoppingDao.removeProduct(2)

            val products = shoppingDao.getAllProducts(1L).getOrAwaitValue()
            assertThat(products.products).doesNotContain(product1)
        }
    }

    @Test
    fun archiveShoppingList(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            shoppingDao.archiveShoppingList(1L)

            val currentLists = shoppingDao.getCurrentShoppingLists().getOrAwaitValue()
            val archivedLists = shoppingDao.getArchivedShoppingLists().getOrAwaitValue()

            shoppingList1.isArchived = 1
            assertThat(currentLists).doesNotContain(shoppingList1)
            assertThat(archivedLists).contains(shoppingList1)
        }
    }

    @Test
    fun checkProduct(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            val product1 = ProductEntity(id = 2, shoppingListId = 1L)
            shoppingDao.addProduct(product1)

            shoppingDao.updateProductCheck(2, 1)
            product1.isInTheCart = 1

            val products = shoppingDao.getAllProducts(1L).getOrAwaitValue()
            assertThat(products.products).contains(product1)
        }
    }

    @Test
    fun isProductChecked(){
        runBlockingTest {
            val shoppingList1 = ShoppingListEntity(id = 1L)
            shoppingDao.addShoppingList(shoppingList1)
            val product1 = ProductEntity(id = 2, shoppingListId = 1L, isInTheCart = 1)
            shoppingDao.addProduct(product1)

            assertThat(shoppingDao.isProductPicked(2)).isEqualTo(1)
        }
    }

}