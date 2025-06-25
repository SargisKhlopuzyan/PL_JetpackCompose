package com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation

sealed class ShoppingListScreens(val route: String) {
    object ShoppingListScreen : ShoppingListScreens("shopping_list_screen")
    object ShoppingListAddScreen : ShoppingListScreens("shopping_list_add_screen")
    object ShoppingListDetailScreen : ShoppingListScreens("shopping_list_detail_screen")
    object ShoppingListImageSearchScreen : ShoppingListScreens("shopping_list_image_search_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}