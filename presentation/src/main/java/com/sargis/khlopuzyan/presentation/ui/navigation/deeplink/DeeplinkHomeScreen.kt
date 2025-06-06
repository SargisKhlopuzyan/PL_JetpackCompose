package com.sargis.khlopuzyan.presentation.ui.navigation.deeplink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DeeplinkHomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.Companion
            .fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                navController.navigate(DeeplinkScreen.DetailScreen.route)
            }) {
                Text(text = "Trigger deeplink")
            }
        }
    }
}

@Preview
@Composable
fun DeeplinkHomeScreenPreview() {
    DeeplinkHomeScreen(rememberNavController())
}