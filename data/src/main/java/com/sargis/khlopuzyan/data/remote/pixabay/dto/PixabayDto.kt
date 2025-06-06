package com.sargis.khlopuzyan.data.remote.pixabay.dto

import com.sargis.khlopuzyan.domain.entity.shoppingList.PixabayDataModel

data class PixabayDto(
    val hits: List<HitDto>,
    val total: Int,
    val totalHits: Int
)

fun PixabayDto.toPixabayDataModel() = PixabayDataModel(
    hits.map { it.toHitDataModel() },
    total,
    totalHits
)
