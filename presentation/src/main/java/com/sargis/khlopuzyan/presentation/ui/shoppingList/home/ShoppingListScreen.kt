package com.sargis.khlopuzyan.presentation.ui.shoppingList.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.sargis.khlopuzyan.commonUi.CommonUiTheme
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiSecondaryButton
import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.presentation.R
import com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation.ShoppingListScreens
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingListViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ShoppingListScreen(navController, uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavController,
    uiState: ShoppingListState,
) {
    val lazyGridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = 0
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        contentWindowInsets = WindowInsets.safeDrawing,
//        contentWindowInsets = WindowInsets.safeContent,
        topBar = {
            CommonTopAppBar("Shopping List") {
                navController.navigateUp()
//                navController.currentBackStackEntry?.destination?.parent.
            }
        },
        floatingActionButton = {
            CommonUiSecondaryButton(
                modifier = Modifier.wrapContentSize(),
                text = "+",
                attributes = CommonUiTheme.buttonStyle.large,
                onClick = {
                    navController.navigate(ShoppingListScreens.ShoppingListAddScreen.route)
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .padding(contentPadding),
            horizontalAlignment = Alignment.Start
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1f),
                columns = GridCells.Fixed(3),
                state = lazyGridState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    uiState.items?.let { items ->
                        items(items.size) { i ->
                            ShoppingListItemView(items[i]) {
                                navController.navigate(
                                    ShoppingListScreens.ShoppingListDetailScreen.withArgs(
                                        items[i].id.toString()
                                    )
                                )
                            }
                        }
                    }
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Total Cost: ${uiState.totalCost}€"
            )
        }
    }
}

@Composable
private fun ShoppingListItemView(shoppingListItem: ShoppingListItem, callback: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .clickable(onClick = {
                callback()
            }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0f),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(shoppingListItem.imageUrl)
                .size(Size.ORIGINAL)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
        )
        Text(modifier = Modifier.padding(top = 4.dp), text = shoppingListItem.name)
    }
}

@Preview
@Composable
fun ShoppingListScreenPreview() {
    val uiState = ShoppingListState()
    ShoppingListScreen(rememberNavController(), uiState)
}