package com.sargis.khlopuzyan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import kotlin.Int

@Entity(tableName = "shopping_list")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val amount: Float,
    val pricePerItem: Float,
    val imageUrl: String
)

fun List<ShoppingListEntity>.toShoppingListItemsList() = map {
    it.toShoppingListItem()
}

fun ShoppingListEntity.toShoppingListItem() = ShoppingListItem(
    id = id,
    name = name,
    amount = amount,
    pricePerItem = pricePerItem,
    imageUrl = imageUrl
)

fun ShoppingListItem.toShoppingListItemEntity() = ShoppingListEntity(
    id = id,
    name = name,
    amount = amount,
    pricePerItem = pricePerItem,
    imageUrl = imageUrl,
)