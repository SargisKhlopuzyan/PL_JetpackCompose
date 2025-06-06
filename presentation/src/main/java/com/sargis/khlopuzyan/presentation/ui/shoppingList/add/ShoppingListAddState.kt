package com.sargis.khlopuzyan.presentation.ui.shoppingList.add

import com.sargis.khlopuzyan.presentation.base.UiState

data class ShoppingListAddState(
    var imgUrl: String = "",
    var name: String = "",
    var amount: String = "",
    var pricePerItem: String = ""
) : UiState