package com.sargis.khlopuzyan.domain.repositories

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun observeLocation(): Flow<Location>
    fun hasLocationPermissions(): Boolean
}