package com.currencies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = main,
    primaryVariant = mainLight,
    secondary = main,
    secondaryVariant = mainLight,
    background = mainGray,
    surface = textGray,
    error = errorRed,
    onPrimary = white,
    onSecondary = white,
    onBackground = inactiveGray,
    onError = white

)

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
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val useDarkIcons = MaterialTheme.colors.isLight
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = white,
        darkIcons = useDarkIcons
    )
    systemUiController.setNavigationBarColor(
        color = white,
        darkIcons = useDarkIcons
    )


    MaterialTheme(
        colors = colors,
        shapes = shapes,
        content = content
    )
}