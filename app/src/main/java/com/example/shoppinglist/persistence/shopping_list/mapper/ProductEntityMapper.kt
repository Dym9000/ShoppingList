package com.example.shoppinglist.persistence.shopping_list.mapper

import com.example.shoppinglist.domain.ProductDomain
import com.example.shoppinglist.persistence.shopping_list.entity.ProductEntity
import com.example.shoppinglist.util.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEntityMapper @Inject constructor(): GenericMapper<ProductEntity, ProductDomain>() {

    override fun mapFrom(input: ProductEntity): ProductDomain {
        return ProductDomain(
            input.id,
            input.shoppingListId,
            input.name,
            input.amount,
            input.isInTheCart
        )
    }

    override fun mapTo(input: ProductDomain): ProductEntity {
        return ProductEntity(
            input.id,
            input.shoppingListId,
            input.name,
            input.amount,
            input.isInTheCart,
        )
    }
}