package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers

sealed interface ScreenEvents {
    data class ShowSnackbar(val message: String) : ScreenEvents
    data class Navigate(val route: String) : ScreenEvents
}