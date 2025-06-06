package com.sargis.khlopuzyan.presentation.ui.shoppingList.add

import com.sargis.khlopuzyan.presentation.base.UiEvent

sealed interface ShoppingListAddUIEvent : UiEvent {
    data class UpdateImageUrl(val imageUrl: String) : ShoppingListAddUIEvent
    data class UpdateName(val name: String) : ShoppingListAddUIEvent
    data class UpdateAmount(val amount: String) : ShoppingListAddUIEvent
    data class UpdatePricePerItem(val pricePerItem: String) : ShoppingListAddUIEvent
    object PickAnImage : ShoppingListAddUIEvent
    object Add : ShoppingListAddUIEvent
}