package com.sargis.khlopuzyan.presentation.navigation.deeplink

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeeplinkDetailScreen(id: Int?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "The id is $id")
    }
}

@Preview
@Composable
fun DeeplinkDetailScreenPreview() {
    DeeplinkDetailScreen(-1)
}