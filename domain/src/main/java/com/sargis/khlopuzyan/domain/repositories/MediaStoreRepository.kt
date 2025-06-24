package com.sargis.khlopuzyan.domain.repositories

import android.graphics.Bitmap

interface MediaStoreRepository {
    suspend fun saveImage(bitmap: Bitmap)
    suspend fun downloadImage(url: String)
}