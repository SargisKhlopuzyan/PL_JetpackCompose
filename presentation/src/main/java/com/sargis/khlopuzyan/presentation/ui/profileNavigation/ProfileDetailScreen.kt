package com.sargis.khlopuzyan.presentation.ui.profileNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileDetailScreen(name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "Hello, $name"
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    ProfileDetailScreen("Test")
}