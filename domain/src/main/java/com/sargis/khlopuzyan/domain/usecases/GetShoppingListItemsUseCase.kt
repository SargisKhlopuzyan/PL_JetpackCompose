package com.sargis.khlopuzyan.domain.usecases

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.repositories.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

interface GetShoppingListItemsUseCase {
    suspend fun getShoppingListItems(): Flow<List<ShoppingListItem>>
}

class GetShoppingListItemsUseCaseImpl(
    private val shoppingListRepository: ShoppingListRepository
) : GetShoppingListItemsUseCase {
    override suspend fun getShoppingListItems(): Flow<List<ShoppingListItem>> {
        return shoppingListRepository.observeAllShoppingListItems()
    }
}