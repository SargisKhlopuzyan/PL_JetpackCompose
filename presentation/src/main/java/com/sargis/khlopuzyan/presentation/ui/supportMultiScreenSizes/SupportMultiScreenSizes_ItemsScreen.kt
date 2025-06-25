package com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar
import com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.navigation.SupportMultiScreenSizesScreen

@Composable
fun SupportMultiScreenSizesItemScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeContent,
        topBar = {
            CommonTopAppBar("Items Screen") {
                navController.popBackStack()
            }
        }
    ) { contentPadding ->
        val windowSize = rememberWindowSize()
        when (windowSize.width) {
            WindowType.Compact -> CompactItemScreen(contentPadding, {
                navController.navigate(
                    SupportMultiScreenSizesScreen.SupportMultiScreenSizesProfileScreen.route
                )
            })

            else -> MediumToExpandedItemScreen(contentPadding, {
                navController.navigate(
                    SupportMultiScreenSizesScreen.SupportMultiScreenSizesProfileScreen.route
                )
            })
        }
    }
}

@Composable
fun CompactItemScreen(contentPadding: PaddingValues, onItemClicked: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        items(8) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {
                        onItemClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item $index",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun MediumToExpandedItemScreen(contentPadding: PaddingValues, onItemClicked: () -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        columns = GridCells.Adaptive(250.dp)
    ) {
        items(8) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {
                        onItemClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item $index",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview
@Composable
fun SupportMultiScreenSizesItemScreenPreview() {
    val navCollection = rememberNavController()
    SupportMultiScreenSizesItemScreen(navCollection)
}