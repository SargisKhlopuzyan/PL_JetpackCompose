package com.sargis.khlopuzyan.presentation.ui.shoppingList.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import com.sargis.khlopuzyan.domain.usecases.GetShoppingListItemUseCase
import com.sargis.khlopuzyan.domain.usecases.SaveImageUseCase
import com.sargis.khlopuzyan.domain.usecases.ShareImageUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingListDetailViewModel(
    val getShoppingListItemUseCase: GetShoppingListItemUseCase,
    val saveImageUseCase: SaveImageUseCase,
    val shareImageUseCase: ShareImageUseCase,
) : BaseViewModel<ShoppingListDetailUiState, ShoppingListDetailUiEvent>() {

    override val _uiState: MutableStateFlow<ShoppingListDetailUiState> =
        MutableStateFlow(ShoppingListDetailUiState())

    override fun onEvent(event: ShoppingListDetailUiEvent) {
        when (event) {
            is ShoppingListDetailUiEvent.LoadShoppingListItem -> {
                loadShoppingListItem(event.shoppingListItemId)
            }

            ShoppingListDetailUiEvent.SaveImage -> {
                saveImageInMediaStore()
            }

            is ShoppingListDetailUiEvent.ShareImage -> {
                shareImage(event.context)
            }
        }
    }

    private fun loadShoppingListItem(id: Long) {
        runOnBackground {
            val shoppingListItem = getShoppingListItemUseCase.getShoppingListItem(id)
            updateUiState {
                it.copy(
                    shoppingListItem = shoppingListItem
                )
            }
        }
    }

    private fun saveImageInMediaStore() {
        runOnBackground {
            uiState.value.shoppingListItem?.let {
                println("save -> it.name: ${it.name}")
                saveImageUseCase.downloadImage(it.imageUrl, it.name)
            }
        }
    }

    private fun shareImage(context: Context) {
        uiState.value.shoppingListItem?.let {
            val imageName = it.name
            val mediaUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }
            println("mediaUri: $mediaUri")

            val imageUri = "${mediaUri}/$imageName".toUri()
            println("imageUri: $imageUri")

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}