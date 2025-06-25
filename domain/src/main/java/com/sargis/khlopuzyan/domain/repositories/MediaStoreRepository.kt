package com.sargis.khlopuzyan.domain.repositories

import android.graphics.Bitmap
import android.net.Uri

interface MediaStoreRepository {
    suspend fun saveImage(bitmap: Bitmap, name: String)
    suspend fun shareImage(uri: Uri)
    suspend fun downloadImage(url: String, name: String)
}