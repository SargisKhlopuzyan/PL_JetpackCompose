package com.sargis.khlopuzyan.presentation.ui.shoppingList.home

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemsUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingListViewModel(
    val getShoppingListItemsUseCase: GetShoppingListItemsUseCase
) : BaseViewModel<ShoppingListState, ShoppingListUIEvent>() {

    override val _uiState: MutableStateFlow<ShoppingListState> =
        MutableStateFlow(ShoppingListState())

    init {
        runOnBackground {
            getShoppingListItemsUseCase.getShoppingListItems().collect {
                val items: List<ShoppingListItem> = it
                val totalCost: Float = items.map { item ->
                    item.amount * item.pricePerItem
                }.sum()

                updateUiState {
                    it.copy(
                        items = items,
                        totalCost = totalCost
                    )
                }
            }
        }
    }

    override fun onEvent(event: ShoppingListUIEvent) {
        when (event) {
            ShoppingListUIEvent.Add -> {

            }
        }
    }
}