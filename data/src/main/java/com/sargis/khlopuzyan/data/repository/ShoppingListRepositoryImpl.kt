package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.local.entity.toShoppingListItem
import com.sargis.khlopuzyan.data.local.entity.toShoppingListItemEntity
import com.sargis.khlopuzyan.data.local.entity.toShoppingListItemsList
import com.sargis.khlopuzyan.data.local.source.ShoppingListDataStore
import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.repositories.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingListRepositoryImpl(
    val shoppingListDataStore: ShoppingListDataStore
) : ShoppingListRepository {
    override fun observeAllShoppingListItems(): Flow<List<ShoppingListItem>> {
        return shoppingListDataStore.observeAllShoppingListItems().map {
            it.toShoppingListItemsList()
        }
    }

    override suspend fun getShoppingListItemById(id: Long): ShoppingListItem? {
        return shoppingListDataStore.getShoppingListItemById(id)?.toShoppingListItem()
    }

    override suspend fun saveShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListDataStore.addShoppingListItem(shoppingListItem.toShoppingListItemEntity())
    }
}