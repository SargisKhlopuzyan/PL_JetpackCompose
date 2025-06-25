package com.sargis.khlopuzyan.domain.usecases

import android.graphics.Bitmap
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository

interface SaveImageUseCase {
    suspend fun saveImage(bitmap: Bitmap, name: String)
    suspend fun downloadImage(url: String, name: String)
}

class SaveImageUseCaseImpl(
    val mediaStoreRepository: MediaStoreRepository,
) : SaveImageUseCase {
    override suspend fun saveImage(bitmap: Bitmap, name: String) {
        mediaStoreRepository.saveImage(bitmap, name)
    }

    override suspend fun downloadImage(url: String, name: String) {
        mediaStoreRepository.downloadImage(url, name)
    }
}