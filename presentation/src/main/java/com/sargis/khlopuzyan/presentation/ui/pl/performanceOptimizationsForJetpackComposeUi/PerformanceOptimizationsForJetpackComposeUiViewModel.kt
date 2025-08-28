package com.sargis.khlopuzyan.presentation.ui.pl.performanceOptimizationsForJetpackComposeUi

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerformanceOptimizationsForJetpackComposeUiViewModel : ViewModel() {

    private val _color: MutableStateFlow<Color> = MutableStateFlow(Color.Green)
    val color = _color.asStateFlow()

    fun setColor(color: Color) {
        _color.update {
            color
        }
    }
}