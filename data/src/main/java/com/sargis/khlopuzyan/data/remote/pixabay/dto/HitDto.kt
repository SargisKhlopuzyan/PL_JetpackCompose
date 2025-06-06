package com.sargis.khlopuzyan.data.remote.pixabay.dto

import com.sargis.khlopuzyan.domain.entity.shoppingList.HitDataModel

data class HitDto(
    val comments: Int,
    val downloads: Int,
    val fullHDURL: String?,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageURL: String?,
    val imageWidth: Int,
    val largeImageURL: String?,
    val likes: Int,
    val pageURL: String?,
    val previewHeight: Int,
    val previewURL: String?,
    val previewWidth: Int,
    val tags: String?,
    val type: String?,
    val user: String?,
    val userImageURL: String?,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String?,
    val webformatWidth: Int
)

fun HitDto.toHitDataModel() = HitDataModel(
    comments,
    downloads,
    fullHDURL,
    id,
    imageHeight,
    imageSize,
    imageURL,
    imageWidth,
    largeImageURL,
    likes,
    pageURL,
    previewHeight,
    previewURL,
    previewWidth,
    tags,
    type,
    user,
    userImageURL,
    user_id,
    views,
    webformatHeight,
    webformatURL,
    webformatWidth
)