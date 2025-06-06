package com.sargis.khlopuzyan.presentation.ui.shoppingList.home

import com.sargis.khlopuzyan.presentation.base.UiEvent

sealed interface ShoppingListUIEvent : UiEvent {
    object Add : ShoppingListUIEvent
}