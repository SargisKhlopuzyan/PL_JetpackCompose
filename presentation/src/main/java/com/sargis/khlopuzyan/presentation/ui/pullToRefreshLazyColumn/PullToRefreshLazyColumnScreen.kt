package com.sargis.khlopuzyan.presentation.ui.pullToRefreshLazyColumn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLazyColumnScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->

        val pullToRefreshState = rememberPullToRefreshState()

        var isRefreshing by remember/*(pullToRefreshState.isAnimating)*/ {
            mutableStateOf(false)
        }

        PullToRefreshBox(
            modifier = Modifier.padding(contentPadding),
            state = pullToRefreshState,
            onRefresh = {
                isRefreshing = true
            },
            isRefreshing = isRefreshing
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(40) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clickable {
                                isRefreshing = false
                            },
                        text = "Item: $it"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PullToRefreshLazyColumnScreenPreview() {
    PullToRefreshLazyColumnScreen()
}