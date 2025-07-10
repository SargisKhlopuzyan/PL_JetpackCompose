package com.sargis.khlopuzyan.presentation.ui.compose.colorBoxScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ColorBoxScreen(
    navController: NavController,
) {
    println("LOG_TAG -> MAIN -> ColorBoxScreen")

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
        ) {
            println("LOG_TAG -> MAIN -> Column -> ColorBoxScreen")

            var color by remember {
                mutableStateOf(Color.LightGray)
            }

            ColorBox(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                color = color,
                updateColor = {
                    color = it
                },
                isStatic = true
            )

            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .background(color)
            )
        }
    }
}