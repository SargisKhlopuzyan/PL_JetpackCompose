package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedStateOfDemo() {

    var counter by remember {
        mutableIntStateOf(0)
    }

//    val counterText = "The counter is $counter"

    val counterText by derivedStateOf {
        "The counter is $counter"
    }

    Button(
        onClick = {
            counter++
        }
    ) {
        Text(text = counterText)
    }
}