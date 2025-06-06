package com.sargis.khlopuzyan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.sargis.khlopuzyan.data.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list WHERE id=:id")
    fun getShoppingListItemById(id: Long): ShoppingListEntity

    @Query("SELECT * FROM shopping_list")
    fun observeAllShoppingListItems(): Flow<List<ShoppingListEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingListItem(shoppingListEntity: ShoppingListEntity)

    @Transaction
    @Query("DELETE FROM shopping_list WHERE id = :id")
    suspend fun deleteShoppingListItem(id: Long)

    @Transaction
    @Upsert
    suspend fun upsertShoppingListItem(shoppingListEntity: ShoppingListEntity)

    @Transaction
    @Update
    suspend fun updateShoppingListItem(shoppingListEntity: ShoppingListEntity)
}