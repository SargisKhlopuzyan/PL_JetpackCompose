package com.sargis.khlopuzyan.domain.usecases

import android.net.Uri
import com.sargis.khlopuzyan.domain.repositories.MediaStoreRepository

interface ShareImageUseCase {
    suspend fun shareImage(uri: Uri)
}

class ShareImageUseCaseImpl(
    val mediaStoreRepository: MediaStoreRepository,
) : ShareImageUseCase {
    override suspend fun shareImage(uri: Uri) {
        mediaStoreRepository.shareImage(uri)
    }
}