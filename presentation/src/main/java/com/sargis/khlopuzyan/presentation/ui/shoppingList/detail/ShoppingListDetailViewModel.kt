package com.sargis.khlopuzyan.presentation.ui.shoppingList.detail

import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemUseCase
import com.sargis.khlopuzyan.domain.usecases.SaveImageUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingListDetailViewModel(
    val getShoppingListItemUseCase: GetShoppingListItemUseCase,
    val saveImageUseCase: SaveImageUseCase,
) : BaseViewModel<ShoppingListDetailUiState, ShoppingListDetailUiEvent>() {

    override val _uiState: MutableStateFlow<ShoppingListDetailUiState> =
        MutableStateFlow(ShoppingListDetailUiState())

    override fun onEvent(event: ShoppingListDetailUiEvent) {
        when (event) {
            is ShoppingListDetailUiEvent.LoadShoppingListItem -> {
                loadShoppingListItem(event.shoppingListItemId)
            }

            ShoppingListDetailUiEvent.SaveImage -> {
                saveImageInMediaStore()
            }

            ShoppingListDetailUiEvent.ShareImage -> {}
        }
    }

    private fun loadShoppingListItem(id: Long) {
        runOnBackground {
            val shoppingListItem = getShoppingListItemUseCase.getShoppingListItem(id)
            updateUiState {
                it.copy(
                    shoppingListItem = shoppingListItem
                )
            }
        }
    }

    private fun saveImageInMediaStore() {
        runOnBackground {
            uiState.value.shoppingListItem?.imageUrl?.let {
                saveImageUseCase.downloadImage(it)
            }
        }
    }
}