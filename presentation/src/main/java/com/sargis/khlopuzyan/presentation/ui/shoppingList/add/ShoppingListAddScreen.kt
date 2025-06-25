package com.sargis.khlopuzyan.presentation.ui.shoppingList.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.sargis.khlopuzyan.commonUi.CommonUiTheme
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiPrimaryButton
import com.sargis.khlopuzyan.presentation.R
import com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation.ShoppingListScreens
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.RESULT_KEY_IMAGE_URL
import com.sargis.khlopuzyan.presentation.util.conditional
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListAddScreen(
    navController: NavController,
    viewModel: ShoppingListAddViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ShoppingListAddScreen(
        navController,
        uiState,
        updateImageUrl = { imageUrl ->
            viewModel.onEvent(ShoppingListAddUIEvent.UpdateImageUrl(imageUrl))
        },
        onNameValueChange = { newTextFieldValue ->
            viewModel.onEvent(ShoppingListAddUIEvent.UpdateName(newTextFieldValue))
        },
        onAmountValueChange = { newTextFieldValue ->
            viewModel.onEvent(ShoppingListAddUIEvent.UpdateAmount(newTextFieldValue))
        },
        onPricePerItemValueChange = { newTextFieldValue ->
            viewModel.onEvent(
                ShoppingListAddUIEvent.UpdatePricePerItem(newTextFieldValue)
            )
        },
        onAddClick = {
            viewModel.onEvent(ShoppingListAddUIEvent.Add)
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListAddScreen(
    navController: NavController,
    uiState: ShoppingListAddState,
    updateImageUrl: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onAmountValueChange: (String) -> Unit,
    onPricePerItemValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
) {
    val imageUrl =
        navController.currentBackStackEntry?.savedStateHandle?.get<String>(RESULT_KEY_IMAGE_URL)

    if (imageUrl != null) {
        updateImageUrl(imageUrl)
        navController.currentBackStackEntry?.savedStateHandle?.remove<String>(RESULT_KEY_IMAGE_URL)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        contentWindowInsets = WindowInsets.safeDrawing,
        contentWindowInsets = WindowInsets.safeContent,
        topBar = {
            CommonTopAppBar("Add shopping list item") {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .conditional(
                            condition = uiState.imgUrl.isEmpty(),
                            ifTrue = {
                                paint(
                                    painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentScale = ContentScale.Crop
                                )
                            },
                        )
                        .clickable(onClick = {
                            navController.navigate(ShoppingListScreens.ShoppingListImageSearchScreen.route)
                        }),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.imgUrl)
                        .size(Size.ORIGINAL)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    value = uiState.name,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    label = {
                        Text("Name")
                    },
                    onValueChange = { newTextFieldValue ->
                        onNameValueChange(newTextFieldValue)
                    }
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    value = uiState.amount.toString(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    maxLines = 1,
                    singleLine = true,
                    label = {
                        Text("Amount")
                    },
                    onValueChange = { newTextFieldValue ->
                        onAmountValueChange(newTextFieldValue)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    value = uiState.pricePerItem.toString(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    label = {
                        Text("Price per item")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal
                    ),
                    onValueChange = { newTextFieldValue ->
                        onPricePerItemValueChange(newTextFieldValue)
                    }
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                CommonUiPrimaryButton(
                    text = "Add",
                    attributes = CommonUiTheme.buttonStyle.medium,
                    onClick = {
                        onAddClick()
                    })
            }
        }
    }
}

@Preview
@Composable
fun ShoppingListAddScreenPreview() {
    val uiState = ShoppingListAddState()
    ShoppingListAddScreen(rememberNavController(), uiState, {}, {}, {}, {}, {})
}