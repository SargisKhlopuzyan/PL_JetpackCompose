package com.sargis.khlopuzyan.commonUi

import android.app.Activity
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiButtonAttributes

@Immutable
data class CommonUiButtonStyle(
    val small: CommonUiButtonAttributes,
    val medium: CommonUiButtonAttributes,
    val large: CommonUiButtonAttributes,
    val dynamicButton: CommonUiButtonAttributes,
)

val LocalCommonUiButtonStyle = staticCompositionLocalOf {
    CommonUiButtonStyle(
        small = CommonUiButtonAttributes.Small,
        medium = CommonUiButtonAttributes.Medium,
        large = CommonUiButtonAttributes.Large,
        dynamicButton = CommonUiButtonAttributes.DynamicButton(),
    )
}

val CommonUiTextSelectionColors = TextSelectionColors(
    handleColor = colorBlack,
    backgroundColor = colorTransparent,
)

@Composable
fun CommonUiTheme(
    colorScheme: ColorScheme = commonUiColorScheme,
    typography: Typography = CommonUiTypography500,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val activity = view.context as? Activity
        SideEffect {
            activity?.window?.statusBarColor = colorScheme.onPrimary.toArgb()
        }
    }

    val replacementButtonStyle = CommonUiButtonStyle(
        small = CommonUiButtonAttributes.Small,
        medium = CommonUiButtonAttributes.Medium,
        large = CommonUiButtonAttributes.Large,
        dynamicButton = CommonUiButtonAttributes.DynamicButton(),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = {
            CompositionLocalProvider(
                LocalIndication provides NoRipple,
                LocalCommonUiButtonStyle provides replacementButtonStyle,
                LocalTextSelectionColors provides CommonUiTextSelectionColors,
                content = content,
            )
        },
    )
}

private object NoRipple : Indication/*, IndicationInstance*/ {
//    @Composable
//    override fun rememberUpdatedInstance(interactionSource: InteractionSource) = this
//    override fun ContentDrawScope.drawIndication() = drawContent()
}

object CommonUiTheme {
    val buttonStyle: CommonUiButtonStyle
        @Composable
        get() = LocalCommonUiButtonStyle.current
}
