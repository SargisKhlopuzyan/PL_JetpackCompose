package com.sargis.khlopuzyan.domain.repositories

import com.sargis.khlopuzyan.domain.entity.shoppingList.PixabayDataModel
import com.sargis.khlopuzyan.domain.util.Result

interface PixabayRepository {
    suspend fun searchImagesByQuery(query: String): Result<PixabayDataModel>
}