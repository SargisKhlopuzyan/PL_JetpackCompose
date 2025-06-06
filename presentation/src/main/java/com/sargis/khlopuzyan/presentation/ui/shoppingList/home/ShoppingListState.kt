package com.sargis.khlopuzyan.presentation.ui.shoppingList.home

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.presentation.base.UiState

data class ShoppingListState(
    var items: List<ShoppingListItem>? = null,
    var totalCost: Float = 0.0f,
) : UiState