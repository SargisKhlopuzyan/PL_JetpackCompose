package com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch

import com.sargis.khlopuzyan.presentation.base.UiEvent

sealed interface ImageSearchUIEvent : UiEvent {
    data class Search(val query: String) : ImageSearchUIEvent
}