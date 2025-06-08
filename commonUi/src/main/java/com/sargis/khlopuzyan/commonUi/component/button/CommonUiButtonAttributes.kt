package com.sargis.khlopuzyan.commonUi.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.commonUi.CommonUiTypography700
import com.sargis.khlopuzyan.commonUi.CommonUiTypography900

sealed class CommonUiButtonAttributes {
    abstract val height: Dp
    abstract val contentPadding: PaddingValues
    abstract val cornerRadius: Dp
    abstract val textStyle: TextStyle
    abstract val iconSize: Dp
    abstract val spaceBetweenIconAndText: Dp
    abstract val borderStrokeWidth: Dp

    data object Small : CommonUiButtonAttributes() {
        override val height = 36.dp
        override val contentPadding = PaddingValues(horizontal = 10.dp)

        //        override val cornerRadius = 8.dp
        override val cornerRadius = 14.dp

        //        override val textStyle = CommonUiTypography900.labelSmall
        override val textStyle = CommonUiTypography900.bodySmall
        override val iconSize = 22.dp
        override val spaceBetweenIconAndText = 6.dp
        override val borderStrokeWidth = 2.dp
    }

    data object Medium : CommonUiButtonAttributes() {
        override val height = 48.dp
        override val contentPadding = PaddingValues(horizontal = 18.dp)

        //        override val cornerRadius = 12.dp
        override val cornerRadius = 18.dp

        //        override val textStyle = CommonUiTypography900.labelMedium
        override val textStyle = CommonUiTypography700.bodyMedium
        override val iconSize = 28.dp
        override val spaceBetweenIconAndText = 8.dp
        override val borderStrokeWidth = 2.dp
    }

    data object Large : CommonUiButtonAttributes() {
        override val height = 56.dp
        override val contentPadding = PaddingValues(horizontal = 24.dp)

        //        override val cornerRadius = 12.dp
        override val cornerRadius = 18.dp

        //        override val textStyle = CommonUiTypography900.labelLarge
        override val textStyle = CommonUiTypography700.bodyLarge
        override val iconSize = 28.dp
        override val spaceBetweenIconAndText = 8.dp
        override val borderStrokeWidth = 2.dp
    }

    data class DynamicButton(
        override val height: Dp = 40.dp,
        override val contentPadding: PaddingValues = PaddingValues(
            horizontal = 4.dp,
            vertical = 8.dp,
        ),
//        override val cornerRadius: Dp = 10.dp,
        override val cornerRadius: Dp = 16.dp,
//        override val textStyle: TextStyle = CommonUiTypography900.bodyMedium,
        override val textStyle: TextStyle = CommonUiTypography700.bodyMedium,
        override val iconSize: Dp = 19.dp,
        override val spaceBetweenIconAndText: Dp = 6.8.dp,
        override val borderStrokeWidth: Dp = 2.dp,
    ) : CommonUiButtonAttributes()
}