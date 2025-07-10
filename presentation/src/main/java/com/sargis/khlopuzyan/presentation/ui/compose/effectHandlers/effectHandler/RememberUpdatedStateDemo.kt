package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay


@Composable
fun RememberUpdatedStateDemo(
    onTimeout: () -> Unit,
) {
    /**
     *  This is also Effect handler
     * */
    // This is in case RememberUpdatedStateDemo will be recomposed lots of time we will have the last callback reference
    val updatedOnTimeout by rememberUpdatedState(
        newValue = onTimeout
    )

    LaunchedEffect(true) {
        delay(3000)
        updatedOnTimeout()
    }
}