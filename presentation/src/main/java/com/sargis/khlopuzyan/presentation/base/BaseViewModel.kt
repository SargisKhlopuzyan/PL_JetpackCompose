package com.sargis.khlopuzyan.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T : UiState, in E : UiEvent> : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<T>

    val uiState: StateFlow<T>
        get() = _uiState

    protected fun updateUiState(newState: (T) -> T) {
        _uiState.update(newState)
    }

    abstract fun onEvent(event: E)

}