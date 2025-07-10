package com.sargis.khlopuzyan.presentation.ui.compose.composeStateTest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposeStateTestScreenViewModel : ViewModel() {

    val testUiStates = mutableListOf(
        TestUiState("a"),
        TestUiState("b"),
        TestUiState("c"),
        TestUiState("d"),
        TestUiState("e"),
        TestUiState("f"),
    )

    fun onTestUiStateChanged(index: Int, state: TestUiState) {
        val text = "${state.text}_$index"
        val newState = TestUiState(text = text)
        _uiState.update {
            it.mapIndexed { i, testUiState ->
                if (i == index) {
                    newState
                } else {
                    testUiState
                }
            }
        }
    }

    private val _uiState: MutableStateFlow<List<TestUiState>> = MutableStateFlow(testUiStates)
    val uiState: StateFlow<List<TestUiState>> = _uiState.asStateFlow()

}