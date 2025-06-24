package com.sargis.khlopuzyan.domain.repositories

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    fun observeAllShoppingListItems(): Flow<List<ShoppingListItem>>
    suspend fun getShoppingListItemById(id: Long): ShoppingListItem?
    suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItem)
}