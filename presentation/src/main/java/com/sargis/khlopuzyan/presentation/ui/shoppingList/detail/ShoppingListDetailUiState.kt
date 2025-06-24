package com.sargis.khlopuzyan.presentation.ui.shoppingList.detail

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.presentation.base.UiState

data class ShoppingListDetailUiState(
    var shoppingListItem: ShoppingListItem? = null
): UiState