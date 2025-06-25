package com.sargis.khlopuzyan.presentation.ui.shoppingList.detail

import android.content.Context
import com.sargis.khlopuzyan.presentation.base.UiEvent

sealed interface ShoppingListDetailUiEvent : UiEvent {
    data class LoadShoppingListItem(val shoppingListItemId: Long) : ShoppingListDetailUiEvent
    object SaveImage : ShoppingListDetailUiEvent
    data class ShareImage(val context: Context) : ShoppingListDetailUiEvent
}