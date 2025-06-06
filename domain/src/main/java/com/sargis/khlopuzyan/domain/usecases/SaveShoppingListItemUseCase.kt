package com.sargis.khlopuzyan.domain.usecases

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.repositories.ShoppingListRepository

interface SaveShoppingListItemUseCase {
    suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItem)
}

class SaveShoppingListItemUseCaseImpl(
    private val shoppingListRepository: ShoppingListRepository
) : SaveShoppingListItemUseCase {
    override suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItem) {
        return shoppingListRepository.saveShoppingListItem(shoppingListItem)
    }
}