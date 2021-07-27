package com.example.shoppinglist.persistence.shopping_list.mapper

import com.example.shoppinglist.domain.ShoppingListDomain
import com.example.shoppinglist.persistence.shopping_list.entity.ShoppingListEntity
import com.example.shoppinglist.util.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListEntityMapper @Inject constructor() :
    GenericMapper<ShoppingListEntity, ShoppingListDomain>() {

    override fun mapFrom(input: ShoppingListEntity): ShoppingListDomain {
        return ShoppingListDomain(
            input.id,
            input.name,
            input.shoppingDate,
            input.isArchived
        )
    }

    override fun mapTo(input: ShoppingListDomain): ShoppingListEntity {
        return ShoppingListEntity(
            input.id,
            input.name,
            input.shoppingDate,
            input.isArchived
        )
    }
}