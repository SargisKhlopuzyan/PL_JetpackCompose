package com.sargis.khlopuzyan.data.repository

import android.graphics.Bitmap
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository

class MediaStoreRepositoryImpl(
    val mediaStoreUtil: MediaStoreUtil,
) : MediaStoreRepository {
    override suspend fun saveImage(bitmap: Bitmap) {
        mediaStoreUtil.saveImage(bitmap)
    }

    override suspend fun downloadImage(url: String) {
        mediaStoreUtil.downloadImage(url)
    }
}