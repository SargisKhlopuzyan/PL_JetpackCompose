package com.sargis.khlopuzyan.domain.entity.shoppingList

data class ShoppingListItem(
    val id: Int,
    val name: String,
    val amount: Float,
    val pricePerItem: Float,
    val imageUrl: String
)