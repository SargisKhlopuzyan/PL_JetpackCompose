package com.sargis.khlopuzyan.commonUi.component.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.commonUi.CommonUiTheme
import com.sargis.khlopuzyan.commonUi.CommonUiTypography500
import com.sargis.khlopuzyan.commonUi.CommonUiTypography900
import com.sargis.khlopuzyan.commonUi.R
import com.sargis.khlopuzyan.commonUi.colorBlack
import com.sargis.khlopuzyan.commonUi.colorGray_14
import com.sargis.khlopuzyan.commonUi.colorWhite
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiButtonAttributes
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiIconButton
import com.sargis.khlopuzyan.commonUi.component.button.CommonUiPrimaryButton


@Composable
fun CommonUiTopAppBar(
    title: String,
    subtitle: String? = null,
    titleTextColor: Color = colorBlack,
    titleTextStyle: TextStyle = CommonUiTypography900.titleMedium,
    subtitleTextStyle: TextStyle = CommonUiTypography500.labelSmall,
    navigationIconResId: Int? = null,
    backgroundColor: Color = colorWhite,
    backgroundShape: Shape = RectangleShape,
    barDividerColor: Color = colorBlack,
    barDividerHeight: Dp = 3.dp,
    barHeight: Dp = 67.dp,
    navigationIconSize: Dp = 24.dp,
    onNavigationIconClick: () -> Unit = {},
    onTitleClick: () -> Unit = {},
    showDivider: Boolean = true,
    actionView: @Composable (RowScope.() -> Unit)? = null,
) = Box(
    Modifier
        .height(barHeight)
        .fillMaxWidth()
        .background(backgroundColor, backgroundShape),
) {
    val hasNavigationIcon = navigationIconResId != null
    val hasActionView = actionView != null
    val hasSubtitle = subtitle != null
    val rowStartPadding = if (hasNavigationIcon) 8.dp else 16.dp

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = rowStartPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (navigationIconResId != null) {
            CommonUiIconButton(
                iconResId = navigationIconResId,
                iconSize = navigationIconSize,
                onClick = onNavigationIconClick,
            )
        }
        val titleStartPadding = if (hasNavigationIcon) 8.dp else 0.dp
        val titleEndPadding = if (hasActionView) 8.dp else 16.dp

        Column(
            modifier = Modifier
                .weight(1F)
                .padding(start = titleStartPadding, end = titleEndPadding)
                .clickable(onClick = onTitleClick),
        ) {
            Text(
                text = title,
                style = titleTextStyle,
                color = titleTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (hasSubtitle) {
                Text(
                    text = subtitle,
                    style = subtitleTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        actionView?.invoke(this)
    }
    if (showDivider) {
        HorizontalDivider(
            thickness = barDividerHeight,
            color = barDividerColor,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
@Preview
fun CommonUiNavigationTopAppBarPreview() {
    CommonUiTheme {
        Column(
            modifier = Modifier
                .background(colorGray_14)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CommonUiTopAppBar("Screen Title")
            CommonUiTopAppBar(
                "Screen Title",
                actionView = {
                    Row(
                        Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        CommonUiIconButton(iconResId = R.drawable.chess, onClick = {})
                    }
                },
            )

            CommonUiTopAppBar(
                "Screen Title",
                "Subtitle",
                navigationIconResId = R.drawable.arrow_left,
                actionView = {
                    Row(
                        Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CommonUiIconButton(iconResId = R.drawable.chess, onClick = {})
                    }
                },
            )

            CommonUiTopAppBar(
                "Screen Title",
                navigationIconResId = R.drawable.close,
                actionView = {
                    Row(
                        Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CommonUiIconButton(
                            iconResId = R.drawable.remove,
                            onClick = {},
                        )
                        CommonUiIconButton(
                            iconResId = R.drawable.chess,
                            onClick = {},
                        )
                    }
                },
            )

            CommonUiTopAppBar(
                "Screen Title",
                navigationIconResId = R.drawable.arrow_left,
                actionView = {
                    Row(
                        Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CommonUiIconButton(
                            iconResId = R.drawable.chess,
                            onClick = {},
                        )
                        CommonUiIconButton(
                            iconResId = R.drawable.remove,
                            onClick = {},
                        )
                        CommonUiIconButton(
                            iconResId = R.drawable.chess,
                            onClick = {},
                        )
                    }
                },
            )
            CommonUiTopAppBar(
                "Screen Title",
                navigationIconResId = R.drawable.arrow_left,
                actionView = {
                    Row(
                        Modifier.padding(end = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CommonUiIconButton(iconResId = R.drawable.remove, onClick = {})
                        CommonUiPrimaryButton(
                            text = "Save".uppercase(),
                            attributes = CommonUiButtonAttributes.Small,
                            onClick = {},
                        )
                    }
                },
            )
            CommonUiTopAppBar(
                "Screen Title",
                navigationIconResId = R.drawable.arrow_left,
                actionView = {
                    Row(
                        Modifier.padding(end = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CommonUiIconButton(iconResId = R.drawable.remove, onClick = {})
                        CommonUiPrimaryButton(
                            iconResId = R.drawable.chess,
                            text = "Save",
                            attributes = CommonUiButtonAttributes.Small,
                            onClick = {},
                        )
                    }
                },
            )
        }
    }
}
