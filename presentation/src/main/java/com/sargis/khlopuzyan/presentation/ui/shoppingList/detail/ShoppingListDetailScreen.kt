package com.sargis.khlopuzyan.presentation.ui.shoppingList.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.sargis.khlopuzyan.commonUi.CommonUiTypography900
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiButtonAttributes
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiPrimaryButton
import com.sargis.khlopuzyan.presentation.R
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShoppingListDetailScreen(
    navController: NavController,
    shoppingListItemId: Long,
    viewModel: ShoppingListDetailViewModel = koinViewModel(),
) {
    viewModel.onEvent(ShoppingListDetailUiEvent.LoadShoppingListItem(shoppingListItemId))

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val current = LocalContext.current
    ShoppingListDetailScreen(
        navController,
        uiState,
        saveImageCallback = {
            viewModel.onEvent(ShoppingListDetailUiEvent.SaveImage)
        },
        shareImageCallback = {
            viewModel.onEvent(ShoppingListDetailUiEvent.ShareImage(current))
        },
    )
}

@Composable
fun ShoppingListDetailScreen(
    navController: NavController,
    uiState: ShoppingListDetailUiState,
    saveImageCallback: () -> Unit,
    shareImageCallback: () -> Unit,
) {
//    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        contentWindowInsets = WindowInsets.safeDrawing,
        contentWindowInsets = WindowInsets.safeContent,
        topBar = {
            CommonTopAppBar("Shopping list item") {
                navController.popBackStack()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uiState.shoppingListItem?.imageUrl)
                    .size(Size.ORIGINAL)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(0.dp)
                        .weight(1f, true),
                    style = CommonUiTypography900.titleMedium,
                    text = uiState.shoppingListItem?.name ?: ""
                )

                CommonUiPrimaryButton(
                    attributes = CommonUiButtonAttributes.Small,
                    text = "Save Image",
                    onClick = {
                        saveImageCallback()
                    }
                )

                CommonUiPrimaryButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    attributes = CommonUiButtonAttributes.Small,
                    text = "Share Image",
                    onClick = {
                        shareImageCallback()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ShoppingListDetailScreenPreview() {
    val navController = rememberNavController()
    val uiState = ShoppingListDetailUiState()
    ShoppingListDetailScreen(navController, uiState, {}, {})
}
