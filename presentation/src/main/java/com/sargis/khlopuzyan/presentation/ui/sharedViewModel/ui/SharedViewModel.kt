package com.sargis.khlopuzyan.presentation.ui.sharedViewModel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _sharedState: MutableStateFlow<String> = MutableStateFlow("BUTTON")
    val sharedState: StateFlow<String> = _sharedState.asStateFlow()

    fun updateState(text: String) {
        _sharedState.update { text }
    }

    override fun onCleared() {
        super.onCleared()
        println("SharedViewModel -> cleared")
    }
}