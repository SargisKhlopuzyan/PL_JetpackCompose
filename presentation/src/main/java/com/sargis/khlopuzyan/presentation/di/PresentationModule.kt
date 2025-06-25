package com.sargis.khlopuzyan.presentation.di

import com.sargis.khlopuzyan.presentation.ui.shoppingList.add.ShoppingListAddViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.detail.ShoppingListDetailViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.ImageSearchViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.home.ShoppingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val viewModelModule = module {
    viewModel {
        ShoppingListViewModel(get())
    }
    viewModel {
        ShoppingListDetailViewModel(get(), get(), get())
    }
    viewModel {
        ShoppingListAddViewModel(get())
    }
    viewModel {
        ImageSearchViewModel(get())
    }
}

val presentationModule = listOf(viewModelModule)