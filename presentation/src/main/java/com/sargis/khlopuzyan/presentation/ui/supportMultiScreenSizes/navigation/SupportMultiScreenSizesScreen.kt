package com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.navigation

sealed class SupportMultiScreenSizesScreen(val route: String) {
    object SupportMultiScreenSizesProfileScreen :
        SupportMultiScreenSizesScreen("support_multi_screen_sizes_profile_screen")
    object SupportMultiScreenSizesItemScreen :
        SupportMultiScreenSizesScreen("support_multi_screen_sizes_item_screen")
}