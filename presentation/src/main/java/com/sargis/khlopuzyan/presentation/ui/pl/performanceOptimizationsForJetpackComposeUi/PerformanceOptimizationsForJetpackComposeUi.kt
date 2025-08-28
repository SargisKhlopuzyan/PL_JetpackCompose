package com.sargis.khlopuzyan.presentation.ui.pl.performanceOptimizationsForJetpackComposeUi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PerformanceOptimizationsForJetpackComposeUi(color: Color, onColorClick: (Color) -> Unit) {
    RgbSelector(
        color = color,
        onColorClick = onColorClick,
        modifier = Modifier.fillMaxSize()
    )
}