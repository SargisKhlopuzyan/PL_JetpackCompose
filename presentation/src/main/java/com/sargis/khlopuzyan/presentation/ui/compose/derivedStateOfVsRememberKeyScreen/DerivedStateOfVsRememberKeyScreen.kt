package com.sargis.khlopuzyan.presentation.ui.compose.derivedStateOfVsRememberKeyScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

//https://www.youtube.com/watch?v=_bb0PVBe3eQ&ab_channel=PhilippLackner
@Composable
fun DerivedStateOfVsRememberKeyScreen() {

    val state = rememberLazyListState()

    var isEnabled by remember {
        mutableStateOf(true)
    }

    Scaffold(
        floatingActionButton = {
            ScrollToTopPosition(state, isEnabled)
        }
    ) { innerPadding ->
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(16.dp).clickable {
                        isEnabled = !isEnabled
                    }
                )
            }
        }
    }
}

@Composable
fun ScrollToTopPosition(
    state: LazyListState,
    isEnabled: Boolean,
) {
    val scope = rememberCoroutineScope()

//    val showScrollToTopButton by remember {
//        derivedStateOf {
//            state.firstVisibleItemIndex >= 5
//        }
//    }

//    val showScrollToTopButton = remember(state.firstVisibleItemIndex) {
//        state.firstVisibleItemIndex >= 5
//    }

    /*****************************************/
    // TODO - This will not work // It will capture the initial values of all the states
//    val showScrollToTopButton by remember {
//        derivedStateOf {
//            state.firstVisibleItemIndex >= 5 && isEnabled
//        }
//    }

    val showScrollToTopButton by remember(isEnabled) {
        derivedStateOf {
            state.firstVisibleItemIndex >= 5 && isEnabled
        }
    }

    if (showScrollToTopButton) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}