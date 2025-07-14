package com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlowsVsColdFlows

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.usecases.LocationUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class KotlinHotFlowsVsColdFlowsViewModel(
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    private val _locationPermissionStateFlow =
        MutableStateFlow(locationUseCase.hasLocationPermissions())
    val locationPermissionStateFlow = _locationPermissionStateFlow.asStateFlow()

    private val _locationStateFlow = MutableStateFlow<Location?>(null)
    val locationStateFlow = _locationStateFlow.asStateFlow()


    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    init {
//        flowDemo()
    }

    fun checkLocationPermission() {
        val hasLocationPermissions = locationUseCase.hasLocationPermissions()
        _locationPermissionStateFlow.tryEmit(hasLocationPermissions)
    }

    fun observeLocation() {
        checkLocationPermission()
        if (locationPermissionStateFlow.value) {
            viewModelScope.launch {
                locationUseCase.observeLocation().collect {
                    _locationStateFlow.tryEmit(it)
                }
            }
        }
    }

    fun observeLocationFlow(): Flow<Location> {
        return locationUseCase.observeLocation()
    }

    fun flowDemo() {
        val flow = flow<Int> {
            repeat(10) {
                emit(10)
                println("LOG_TAG -> Emitted $it")
                delay(1000)
            }
        }

        val job = flow.launchIn(viewModelScope)
        // vs
        val job2 = viewModelScope.launch {
            flow.collect {

            }
        }
    }
}