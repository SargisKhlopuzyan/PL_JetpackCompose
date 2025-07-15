package com.sargis.khlopuzyan.presentation.di

import com.sargis.khlopuzyan.presentation.ui.compose.composeStateTest.ComposeStateTestScreenViewModel
import com.sargis.khlopuzyan.presentation.ui.compose.coroutineCancellationAndExceptionHandling.CoroutineCancellationAndExceptionHandlingScreen
import com.sargis.khlopuzyan.presentation.ui.compose.coroutineCancellationAndExceptionHandling.CoroutineCancellationAndExceptionHandlingViewModel
import com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.LaunchedEffectViewModel
import com.sargis.khlopuzyan.presentation.ui.compose.kotlinFlows.KotlinFlowsViewModel
import com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlows.KotlinHotFlowsViewModel
import com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlowsVsColdFlows.KotlinHotFlowsVsColdFlowsViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.add.ShoppingListAddViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.detail.ShoppingListDetailViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.home.ShoppingListViewModel
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.ImageSearchViewModel
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
    viewModel {
        ComposeStateTestScreenViewModel()
    }
    viewModel {
        LaunchedEffectViewModel()
    }
    viewModel {
        KotlinFlowsViewModel()
    }
    viewModel {
        KotlinHotFlowsViewModel()
    }
    viewModel {
        KotlinHotFlowsVsColdFlowsViewModel(get())
    }
    viewModel {
        CoroutineCancellationAndExceptionHandlingViewModel(get())
    }
}

val presentationModule = listOf(viewModelModule)