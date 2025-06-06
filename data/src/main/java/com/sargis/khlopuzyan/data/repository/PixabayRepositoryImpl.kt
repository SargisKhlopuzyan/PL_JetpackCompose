package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.BuildConfig
import com.sargis.khlopuzyan.data.remote.pixabay.PixabayApi
import com.sargis.khlopuzyan.data.remote.pixabay.dto.toPixabayDataModel
import com.sargis.khlopuzyan.domain.entity.shoppingList.PixabayDataModel
import com.sargis.khlopuzyan.domain.repositories.PixabayRepository
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UiError

class PixabayRepositoryImpl(
    private val pixabayApi: PixabayApi
) : PixabayRepository {
    override suspend fun searchImagesByQuery(query: String): Result<PixabayDataModel> {
        return try {
            val pixabayDataModel = pixabayApi.getSearchLocationByQuery(
                query,
                BuildConfig.PIXABAY_API_KEY
            ).toPixabayDataModel()

            Result.Success(pixabayDataModel)
        } catch (e: Exception) {
            Result.Error(UiError.UnknownError)
        }
    }
}