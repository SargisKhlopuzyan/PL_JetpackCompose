package com.sargis.khlopuzyan.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.sargis.khlopuzyan.presentation.ui.compose.navigation.ComposeScreen

@Composable
fun JetpackComposeMainScreen(
    navController: NavController,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(contentPadding),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            item {
                LazyGridItem("Compose State Test") {
                    navController.navigate(ComposeScreen.ComposeStateTestScreen.route)
                }
            }

            item {
                LazyGridItem("Color Box") {
                    navController.navigate(ComposeScreen.ColorBoxScreen.route)
                }
            }

            item {
                LazyGridItem("Styling Text") {
                    navController.navigate(ComposeScreen.StylingTextScreen.route)
                }
            }

            item {
                LazyGridItem("Textfields, Buttons, Snackbars") {
                    navController.navigate(ComposeScreen.TextfieldsButtonsSnackbarsScreen.route)
                }
            }

            item {
                LazyGridItem("Effect Handlers") {
                    navController.navigate(ComposeScreen.EffectHandlersScreen.route)
                }
            }

            item {
                LazyGridItem("Kotlin Flows") {
                    navController.navigate(ComposeScreen.KotlinFlowsScreen.route)
                }
            }

            item {
                LazyGridItem("Kotlin Hot Flows") {
                    navController.navigate(ComposeScreen.KotlinHotFlowsScreen.route)
                }
            }

            item {
                LazyGridItem("Hot Flows vs. Cold Flows") {
                    navController.navigate(ComposeScreen.KotlinHotFlowsVsColdFlowsScreen.route)
                }
            }

            item {
                LazyGridItem("Coroutine cancellation & exception handling") {
                    navController.navigate(ComposeScreen.CoroutineCancellationAndExceptionHandlingScreen.route)
                }
            }
            item {
                LazyGridItem("Performance Optimization with @Stable and @Immutable") {
                    navController.navigate(ComposeScreen.PerformanceOptimizationWithStableAndImmutable.route)
                }
            }
            item {
                LazyGridItem("derivedStateOf VS. remember(key)") {
                    navController.navigate(ComposeScreen.DerivedStateOfVsRememberKeyScreen.route)
                }
            }
        }
    }
}

@Composable
fun LazyGridItem(text: String, action: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(188, 241, 149, 255)),
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

@Preview(showBackground = true)
@Composable
fun JetpackComposeMainScreenPreview() {
    JetpackComposeMainScreen(rememberNavController())
}