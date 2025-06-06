package com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch

import com.sargis.khlopuzyan.domain.entity.shoppingList.PixabayDataModel
import com.sargis.khlopuzyan.domain.usecases.SearchImagesUseCase
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.UiError
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import com.sargis.khlopuzyan.presentation.base.runOnBackground
import kotlinx.coroutines.flow.MutableStateFlow

class ImageSearchViewModel(
    private val searchImagesUseCase: SearchImagesUseCase
) : BaseViewModel<ImageSearchState, ImageSearchUIEvent>() {

//    override val _uiState: MutableStateFlow<ImageSearchState>
//        get() = MutableStateFlow(ImageSearchState())

    override val _uiState: MutableStateFlow<ImageSearchState> = MutableStateFlow(ImageSearchState())

    override fun onEvent(event: ImageSearchUIEvent) {
        when (event) {
            is ImageSearchUIEvent.Search -> {
                searchImagesByQuery(event.query)
            }
        }
    }

    private fun searchImagesByQuery(query: String) {
        runOnBackground {
            showLoading()
            val result = searchImagesUseCase.searchImagesByQuery(query)
            handleImageSearchResponse(result)
        }
    }

    private fun showLoading() {
        updateUiState {
            it.copy(
                isLoading = true,
                uiError = null
            )
        }
    }

    private fun handleImageSearchResponse(result: Result<PixabayDataModel>) {
        when (result) {
            is Result.Success<PixabayDataModel> -> {
                val imageURLs = result.data?.hits?.mapNotNull {
                    it.largeImageURL
                } ?: emptyList()

                updateUiState {
                    it.copy(
                        imgUrls = imageURLs,
                        isLoading = false,
                        uiError = null
                    )
                }
            }

            is Result.Error<*> -> {
                updateUiState {
                    it.copy(
                        imgUrls = emptyList(),
                        isLoading = false,
                        uiError = it.uiError ?: UiError.UnknownError
                    )
                }
            }
        }
    }
}