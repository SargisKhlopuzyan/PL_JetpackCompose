package com.sargis.khlopuzyan.presentation.ui.shoppingList.add

import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.domain.usecases.SaveShoppingListItemUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ShoppingListAddViewModel(
    val saveShoppingListItemUseCase: SaveShoppingListItemUseCase
) : BaseViewModel<ShoppingListAddState, ShoppingListAddUIEvent>() {

    override val _uiState: MutableStateFlow<ShoppingListAddState> =
        MutableStateFlow(ShoppingListAddState())

    override val uiState: StateFlow<ShoppingListAddState> = _uiState.onStart {
        loadData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ShoppingListAddState()
    )

    private fun loadData() {

    }

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
            val amount = try {
                uiState.value.amount.toFloat()
            } catch (e: Exception) {
                0.0f
            }
            val pricePerItem = try {
                uiState.value.pricePerItem.toFloat()
            } catch (e: Exception) {
                0.0f
            }
            val imgUrl = uiState.value.imgUrl
            val shoppingListItem = ShoppingListItem(0, name, amount, pricePerItem, imgUrl)
            saveShoppingListItemUseCase.saveShoppingListItem(shoppingListItem)
        }
    }
}