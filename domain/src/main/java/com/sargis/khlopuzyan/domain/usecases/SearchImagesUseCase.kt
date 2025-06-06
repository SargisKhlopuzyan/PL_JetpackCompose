package com.sargis.khlopuzyan.domain.usecases

import com.sargis.khlopuzyan.domain.entity.shoppingList.PixabayDataModel
import com.sargis.khlopuzyan.domain.repositories.PixabayRepository
import com.sargis.khlopuzyan.domain.util.Result

interface SearchImagesUseCase {
    suspend fun searchImagesByQuery(query: String): Result<PixabayDataModel>
}

class SearchImagesUseCaseImpl(
    private val pixabayRepository: PixabayRepository
) : SearchImagesUseCase {
    override suspend fun searchImagesByQuery(query: String): Result<PixabayDataModel> {
        return pixabayRepository.searchImagesByQuery(query)
    }
}