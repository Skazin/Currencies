package com.currencies.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = main,
    primaryVariant = mainDark,
    secondary = main,
    secondaryVariant = mainDark,
    background = backGray,
    surface = inactiveGray,
    error = errorRed,
    onPrimary = white,
    onSecondary = white,
    onBackground = textGray,
    onError = white
)

@Composable
fun CurrenciesTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        shapes = shapes,
        content = content
    )
}