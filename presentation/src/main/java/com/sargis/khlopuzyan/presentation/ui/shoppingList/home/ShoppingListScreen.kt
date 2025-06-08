package com.sargis.khlopuzyan.presentation.ui.shoppingList.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.sargis.khlopuzyan.commonUi.CommonUiTheme
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiSecondaryButton
import com.sargis.khlopuzyan.domain.entity.shoppingList.ShoppingListItem
import com.sargis.khlopuzyan.presentation.R
import com.sargis.khlopuzyan.presentation.ui.shoppingList.ShoppingListScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val lazyGridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = 0
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.BottomStart
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                columns = GridCells.Fixed(3),
                state = lazyGridState,
                content = {
                    items(uiState.items?.size ?: 0) { i ->
                        ShoppingListItemView(uiState.items!![i]) {
                            navController.navigate(ShoppingListScreens.ShoppingListDetailScreen.route)
                        }
                    }
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
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
            .fillMaxSize()
            .padding(4.dp)
            .background(Color.White)
            .clickable(onClick = {
                callback()
            }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .size(64.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(shoppingListItem.imageUrl)
                .size(Size.ORIGINAL)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
        )
        Text(shoppingListItem.name)
    }
}

@Preview
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen(rememberNavController())
}