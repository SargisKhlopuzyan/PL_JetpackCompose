package com.sargis.khlopuzyan.presentation.ui.shoppingList.add

import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.usecases.SaveShoppingListItemUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingListAddViewModel(
    val saveShoppingListItemUseCase: SaveShoppingListItemUseCase
) : BaseViewModel<ShoppingListAddState, ShoppingListAddUIEvent>() {

    override val _uiState: MutableStateFlow<ShoppingListAddState> =
        MutableStateFlow(ShoppingListAddState())

    override fun onEvent(event: ShoppingListAddUIEvent) {
        when (event) {
            is ShoppingListAddUIEvent.UpdateName -> {
                updateUiState {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is ShoppingListAddUIEvent.UpdateAmount -> {
                updateUiState {
                    it.copy(
                        amount = event.amount
                    )
                }
            }

            is ShoppingListAddUIEvent.UpdatePricePerItem -> {
                updateUiState {
                    it.copy(
                        pricePerItem = event.pricePerItem
                    )
                }
            }

            is ShoppingListAddUIEvent.UpdateImageUrl -> {
                updateUiState {
                    it.copy(
                        imgUrl = event.imageUrl
                    )
                }
            }

            ShoppingListAddUIEvent.PickAnImage -> {
            }

            ShoppingListAddUIEvent.Add -> {
                saveShoppingListItem()
            }
        }
    }

    private fun saveShoppingListItem() {
        runOnBackground {
            val name = uiState.value.name
            val amount = uiState.value.amount.toFloat()
            val pricePerItem = uiState.value.pricePerItem.toFloat()
            val imgUrl = uiState.value.imgUrl
            val shoppingListItem = ShoppingListItem(0, name, amount, pricePerItem, imgUrl)
            saveShoppingListItemUseCase.saveShoppingListItem(shoppingListItem)
        }
    }
}