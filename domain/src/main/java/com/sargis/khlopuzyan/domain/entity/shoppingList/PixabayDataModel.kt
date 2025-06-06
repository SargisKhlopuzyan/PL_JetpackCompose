package com.sargis.khlopuzyan.domain.entity.shoppingList

data class PixabayDataModel(
    val hits: List<HitDataModel>,
    val total: Int,
    val totalHits: Int
)
