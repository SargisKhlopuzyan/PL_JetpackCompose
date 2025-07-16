package com.sargis.khlopuzyan.presentation.ui.compose.performanceOptimizationWithStableAndImmutableScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//https://www.youtube.com/watch?v=_FtKhWvHiTg&ab_channel=PhilippLackner
@Composable
fun PerformanceOptimizationWithStableAndImmutableScreen() {
    Scaffold { contentPadding ->

        var selected by remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.padding(contentPadding)) {
            Checkbox(
                checked = selected,
                onCheckedChange = {
                    selected = !selected
                }
            )
            ContactList(
                state = ContactListState(
                    isLoading = false,
                    names = listOf("Peter")
                )
            )
        }
    }
}

@Composable
fun ContactList(state: ContactListState) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(state.names.toString())
        }
    }
}

// @Immutable is more strong promise that fields will not be changed
//
//@Immutable
@Stable
data class ContactListState(
    val isLoading: Boolean,
    val names: List<String>,
)