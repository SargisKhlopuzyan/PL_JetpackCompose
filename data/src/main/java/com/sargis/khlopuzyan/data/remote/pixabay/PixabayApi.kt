package com.sargis.khlopuzyan.data.remote.pixabay

import com.sargis.khlopuzyan.data.remote.pixabay.dto.PixabayDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    suspend fun getSearchLocationByQuery(
        @Query("q") query: String,
        @Query("key") accessKey: String,
        @Query("image_type") imageType: String = "photo",
        @Query("pretty") pretty: Boolean = true,
    ): PixabayDto
}