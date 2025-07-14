package com.sargis.khlopuzyan.domain.usecases

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.repositories.ShoppingListRepository

interface GetShoppingListItemUseCase {
    suspend fun getShoppingListItem(id: Long): ShoppingListItem?
}

class GetShoppingListItemUseCaseImpl(
    private val shoppingListRepository: ShoppingListRepository,
) : GetShoppingListItemUseCase {
    override suspend fun getShoppingListItem(id: Long): ShoppingListItem? {
        return shoppingListRepository.getShoppingListItemById(id)
    }
}