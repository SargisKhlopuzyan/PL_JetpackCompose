package com.sargis.khlopuzyan.presentation.ui.sharedViewModel.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Screen2(sharedState: State<String>, updateCallback: (String) -> Unit) {
    Button(
        modifier = Modifier.padding(50.dp),
        onClick = {
            updateCallback("abcdef")
        }) {
        Text(text = sharedState.value)
    }
}