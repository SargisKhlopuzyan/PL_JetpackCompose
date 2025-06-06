package com.sargis.khlopuzyan.data.remote.pixabay

import com.sargis.khlopuzyan.data.BuildConfig
import com.sargis.khlopuzyan.data.remote.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object PixabayApiRetrofitBuilder {
    private const val PIXABAY_BASE_URL = "https://pixabay.com/"

    fun build(): PixabayApi {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(ConnectivityInterceptor())
            connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(it)
                }
            }
        }.build()
        return Retrofit.Builder()
            .baseUrl(PIXABAY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .client(okHttpClient)
            .build()
            .create()
    }
}