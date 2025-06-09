package com.sargis.khlopuzyan.presentation.ui.navigation.lazy_grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.navigation.main.MainScreen
import kotlinx.coroutines.launch

@Composable
fun LazyGridScreen(navController: NavController) {
    val state = rememberLazyGridState(
//        initialFirstVisibleItemIndex = 99
        initialFirstVisibleItemIndex = 0
    )

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(index = 99)
                    }
                }
            ) {
                Text(text = "Scroll to 99")
            }
            val screens = listOf("Profile", "Bottom Navigation", "Deeplink", "Shopping")
            LazyVerticalGrid(
                modifier = Modifier.fillMaxHeight(),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = state,
//        columns = GridCells.Adaptive(100.dp),
                content = {
                    item {
                        LazyGridItem(screens[0], {
                            navController.navigate(MainScreen.ProfileScreen.route)
                        })
                    }
                    item {
                        LazyGridItem(screens[1], {
                            navController.navigate(MainScreen.BtmNavMainScreen.route)
                        })
                    }
                    item {
                        LazyGridItem(screens[2], {
                            navController.navigate(MainScreen.DeeplinkScreen.route)
                        })
                    }
                    item {
                        LazyGridItem(screens[3], {
                            navController.navigate(MainScreen.ShoppingScreen.route)
                        })
                    }
                    items(100) { i ->
                        LazyGridItem("TODO - $i", {})
                    }
                }
            )
        }
    }
}

@Composable
fun LazyGridItem(text: String, action: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(0.dp)),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            onClick = action
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 13.sp,
                text = text
            )
        }
    }
}

@Preview
@Composable
fun LazyGridScreenPreview() {
    val navController = rememberNavController()
    LazyGridScreen(navController)
}