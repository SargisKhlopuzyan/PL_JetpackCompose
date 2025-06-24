package com.sargis.khlopuzyan.domain.usecases

import android.graphics.Bitmap
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository

interface SaveImageUseCase {
    suspend fun saveImage(bitmap: Bitmap)
    suspend fun downloadImage(url: String)
}

class SaveImageUseCaseImpl(
    val mediaStoreRepository: MediaStoreRepository,
) : SaveImageUseCase {
    override suspend fun saveImage(bitmap: Bitmap) {
        mediaStoreRepository.saveImage(bitmap)
    }

    override suspend fun downloadImage(url: String) {
        mediaStoreRepository.downloadImage(url)
    }
}