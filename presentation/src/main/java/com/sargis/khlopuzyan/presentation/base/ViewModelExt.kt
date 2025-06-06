package com.sargis.khlopuzyan.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T> ViewModel.runOnBackground(block: suspend () -> T) {
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }
}