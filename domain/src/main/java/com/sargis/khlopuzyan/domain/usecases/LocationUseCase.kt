package com.sargis.khlopuzyan.domain.usecases

import android.location.Location
import com.sargis.khlopuzyan.domain.repositories.LocationRepository
import kotlinx.coroutines.flow.Flow

interface LocationUseCase {
    fun observeLocation(): Flow<Location>
    fun hasLocationPermissions(): Boolean
}

class LocationUseCaseImpl(
    val locationRepository: LocationRepository,
) : LocationUseCase {
    override fun observeLocation(): Flow<Location> {
        return locationRepository.observeLocation()
    }

    override fun hasLocationPermissions(): Boolean {
        return locationRepository.hasLocationPermissions()
    }
}