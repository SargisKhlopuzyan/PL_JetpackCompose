package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemUseCase
import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemUseCaseImpl
import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemsUseCase
import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemsUseCaseImpl
import com.sargis.khlopuzyan.domain.usecases.SaveImageUseCase
import com.sargis.khlopuzyan.domain.usecases.SaveImageUseCaseImpl
import com.sargis.khlopuzyan.domain.usecases.SaveShoppingListItemUseCase
import com.sargis.khlopuzyan.domain.usecases.SaveShoppingListItemUseCaseImpl
import com.sargis.khlopuzyan.domain.usecases.SearchImagesUseCase
import com.sargis.khlopuzyan.domain.usecases.SearchImagesUseCaseImpl
import org.koin.dsl.module

private val useCaseModule = module {
    single<SearchImagesUseCase> { SearchImagesUseCaseImpl(get()) }
    single<GetShoppingListItemsUseCase> { GetShoppingListItemsUseCaseImpl(get()) }
    single<GetShoppingListItemUseCase> { GetShoppingListItemUseCaseImpl(get()) }
    single<SaveShoppingListItemUseCase> { SaveShoppingListItemUseCaseImpl(get()) }
    single<SaveImageUseCase> { SaveImageUseCaseImpl(get()) }
}

val domainModule = listOf(useCaseModule/*, utilsModule*/)
