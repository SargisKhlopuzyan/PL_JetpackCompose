package com.sargis.khlopuzyan.data.repository

import android.location.Location
import com.sargis.khlopuzyan.data.location.LocationHelper
import com.sargis.khlopuzyan.domain.repositories.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(
    val locationHelper: LocationHelper
): LocationRepository {
    override fun observeLocation(): Flow<Location> {
        return locationHelper.observeLocation()
    }

    override fun hasLocationPermissions(): Boolean {
        return locationHelper.hasLocationPermissions()
    }

}