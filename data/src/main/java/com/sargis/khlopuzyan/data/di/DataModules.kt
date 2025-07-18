package com.sargis.khlopuzyan.data.di

import com.sargis.khlopuzyan.data.local.dao.ShoppingListDao
import com.sargis.khlopuzyan.data.local.db.ShoppingListDatabase
import com.sargis.khlopuzyan.data.local.source.ShoppingListDataStore
import com.sargis.khlopuzyan.data.local.source.ShoppingListDataStoreImpl
import com.sargis.khlopuzyan.data.location.LocationHelper
import com.sargis.khlopuzyan.data.remote.pixabay.PixabayApi
import com.sargis.khlopuzyan.data.remote.pixabay.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.data.repository.LocationRepositoryImpl
import com.sargis.khlopuzyan.data.repository.MediaStoreRepositoryImpl
import com.sargis.khlopuzyan.data.repository.MediaStoreUtil
import com.sargis.khlopuzyan.data.repository.PixabayRepositoryImpl
import com.sargis.khlopuzyan.data.repository.ShoppingListRepositoryImpl
import com.sargis.khlopuzyan.domain.repositories.LocationRepository
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository
import com.sargis.khlopuzyan.domain.repositories.PixabayRepository
import com.sargis.khlopuzyan.domain.repositories.ShoppingListRepository
import org.koin.dsl.module

private val databaseModule = module {
    single<ShoppingListDatabase> {
        ShoppingListDatabase.getInstance(get())
    }
    single<ShoppingListDao> {
        get<ShoppingListDatabase>().shoppingListDao()
    }
    single<ShoppingListDataStore> {
        ShoppingListDataStoreImpl(get())
    }
}

private val repositoryModule = module {
    single<PixabayApi> { PixabayApiRetrofitBuilder.build() }
    single<PixabayRepository> { PixabayRepositoryImpl(get()) }
    single<ShoppingListRepository> { ShoppingListRepositoryImpl(get()) }
    single<MediaStoreUtil> { MediaStoreUtil(get()) }
    single<MediaStoreRepository> { MediaStoreRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<LocationHelper> { LocationHelper(get()) }
}

private val utilitiesModule = module {
}

val dataModules = listOf(databaseModule, repositoryModule, utilitiesModule)