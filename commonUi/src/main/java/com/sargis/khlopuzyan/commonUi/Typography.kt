package com.sargis.khlopuzyan.commonUi

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val fontLato = FontFamily(
    Font(
        resId = R.font.lato_regular,
    ),
    Font(
        resId = R.font.lato_medium,
        weight = FontWeight.Medium,
    ),
    Font(
        resId = R.font.lato_italic,
        style = FontStyle.Italic,
    ),
    Font(
        resId = R.font.lato_thin,
        weight = FontWeight.Thin,
    ),
    Font(
        resId = R.font.lato_thin_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic,
    ),
    Font(
        resId = R.font.lato_bold,
        weight = FontWeight.Bold,
    ),
    Font(
        resId = R.font.lato_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic,
    ),
    Font(
        resId = R.font.lato_light,
        weight = FontWeight.Light,
    ),
    Font(
        resId = R.font.lato_light_italic,
        weight = FontWeight.Light,
        style = FontStyle.Italic,
    ),
    Font(
        resId = R.font.lato_black,
        weight = FontWeight.Black,
    ),
    Font(
        resId = R.font.lato_black_italic,
        weight = FontWeight.Black,
        style = FontStyle.Italic,
    )
)

val CommonUiTypography500 = createTypography(FontWeight.W500)
val CommonUiTypography700 = createTypography(FontWeight.W700) // weight for exclusive use only (not part of the design system)
val CommonUiTypography900 = createTypography(FontWeight.W900)

private fun createTypography(fontWeight: FontWeight): Typography {
    val typography = Typography(
        headlineLarge = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 36.sp,
            lineHeight = 40.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 31.sp,
            lineHeight = 31.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 25.sp,
            lineHeight = 28.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 23.sp,
            lineHeight = 25.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 23.sp,
            lineHeight = 28.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 21.sp,
            lineHeight = 25.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 18.sp,
            lineHeight = 22.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 23.sp,
            lineHeight = 23.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 21.sp,
            lineHeight = 23.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 18.sp,
            lineHeight = 18.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = fontLato,
            fontWeight = fontWeight,
            fontSize = 15.sp,
            lineHeight = 15.sp,
        ),
    )
    return typography
}