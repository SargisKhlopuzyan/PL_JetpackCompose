package com.sargis.khlopuzyan.commonUi.component.button

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.commonUi.CommonUiTheme
import com.sargis.khlopuzyan.commonUi.R


@Composable
fun CommonUiIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    imageVector: ImageVector? = null,
    iconSize: Dp = 24.dp,
    touchAreaPadding: PaddingValues = PaddingValues(8.dp),
    iconTintColor: Color = Color.Black,
    contentDescription: String = "",
    backgroundColor: Color = Color.Unspecified,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    requireNotNull(iconResId ?: imageVector) {
        stringResource(R.string.button_resource_runtime_error)
    }
    when {
        iconResId != null -> {
            Icon(
                modifier = modifier
                    .clip(shape)
                    .clickable(
                        onClick = onClick,
                        enabled = enabled,
                    )
                    .background(backgroundColor)
                    .padding(touchAreaPadding)
                    .size(iconSize),
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                tint = iconTintColor,
            )
        }

        imageVector != null -> {
            Icon(
                modifier = modifier
                    .clip(shape)
                    .clickable(
                        onClick = onClick,
                        enabled = enabled,
                    )
                    .background(backgroundColor)
                    .padding(touchAreaPadding)
                    .size(iconSize),
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = iconTintColor,
            )
        }
    }
}

@Composable
@Preview
private fun KompaktIconButtonPreview() {
    val context = LocalContext.current

    val toastMsg = "CLICK"
    CommonUiTheme {
        CommonUiIconButton(
            iconResId = R.drawable.chess,
            contentDescription = "Icon button",
            onClick = { Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show() },
        )
    }
}