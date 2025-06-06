package com.sargis.khlopuzyan.data.local.source

import com.sargis.khlopuzyan.data.local.dao.ShoppingListDao
import com.sargis.khlopuzyan.data.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListDataStore {
    suspend fun addShoppingListItem(shoppingListEntity: ShoppingListEntity)
    fun observeAllShoppingListItems(): Flow<List<ShoppingListEntity>>
    fun getShoppingListItemById(id: Long): ShoppingListEntity
    suspend fun upsertShoppingListItem(shoppingListEntity: ShoppingListEntity)
    suspend fun deleteShoppingListItem(id: Long)
}

class ShoppingListDataStoreImpl(
    private val dao: ShoppingListDao
) : ShoppingListDataStore {
    override suspend fun addShoppingListItem(shoppingListEntity: ShoppingListEntity) {
        dao.insertShoppingListItem(shoppingListEntity)
    }

    override fun observeAllShoppingListItems(): Flow<List<ShoppingListEntity>> {
        return dao.observeAllShoppingListItems()
    }

    override fun getShoppingListItemById(id: Long): ShoppingListEntity {
        return dao.getShoppingListItemById(id)
    }

    override suspend fun upsertShoppingListItem(shoppingListEntity: ShoppingListEntity) {
        dao.upsertShoppingListItem(shoppingListEntity)
    }

    override suspend fun deleteShoppingListItem(id: Long) {
        return dao.deleteShoppingListItem(id)
    }

}