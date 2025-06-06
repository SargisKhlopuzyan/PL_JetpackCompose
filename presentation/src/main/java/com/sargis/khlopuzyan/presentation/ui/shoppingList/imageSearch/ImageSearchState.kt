package com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch

import com.sargis.khlopuzyan.domain.util.UiError
import com.sargis.khlopuzyan.presentation.base.UiState

data class ImageSearchState(
    var query: String = "",
    var isLoading: Boolean = false,
    var uiError: UiError? = null,
    var imgUrls: List<String>? = null,
) : UiState