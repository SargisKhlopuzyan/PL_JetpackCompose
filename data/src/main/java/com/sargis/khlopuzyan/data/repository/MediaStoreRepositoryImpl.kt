package com.sargis.khlopuzyan.data.repository

import android.graphics.Bitmap
import android.net.Uri
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository

class MediaStoreRepositoryImpl(
    val mediaStoreUtil: MediaStoreUtil,
) : MediaStoreRepository {
    override suspend fun saveImage(bitmap: Bitmap, name: String) {
        mediaStoreUtil.saveImage(bitmap, name)
    }

    override suspend fun shareImage(uri: Uri) {
        mediaStoreUtil.shareImage(uri)
    }

    override suspend fun downloadImage(url: String, name: String) {
        mediaStoreUtil.downloadImage(url, name)
    }
}