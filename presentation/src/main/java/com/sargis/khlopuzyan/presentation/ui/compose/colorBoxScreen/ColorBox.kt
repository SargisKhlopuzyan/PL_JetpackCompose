package com.sargis.khlopuzyan.presentation.ui.compose.colorBoxScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit,
    color: Color,
    isStatic: Boolean = false,
) {
    println("LOG_TAG -> CHILD -> ColorBox")

    var textValue by remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .background(if (isStatic) Color.Red else color)
            .clickable {
                val randomColor = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f,
                )
                updateColor(randomColor)
            }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textValue,
            onValueChange = {
                textValue = it
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorBoxPreview() {
    ColorBox(
        modifier = Modifier.fillMaxSize(),
        updateColor = {},
        color = Color.Cyan
    )
}