package com.currencies.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.currencies.R
import com.currencies.ui.base.Destinations
import com.currencies.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@InternalCoroutinesApi
@Composable
fun SplashView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val onLauncherComplete = { navController.navigate(Destinations.HOME.destinationName) }
    val currentOnTimeout by rememberUpdatedState(onLauncherComplete)

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
        currentOnTimeout()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        gradientStart,
                        gradientUpperMiddle,
                        gradientLowerMiddle,
                        gradientEnd
                    )
                )
            )
    ) {

        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        //Working with statusBar and navigationBar colors and icons
        SideEffect {
            systemUiController.setStatusBarColor(
                color = gradientStart,
                darkIcons = !useDarkIcons
            )
            systemUiController.setNavigationBarColor(
                color = gradientEnd,
                darkIcons = !useDarkIcons
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_currencies_logo),
                contentDescription = null,
                tint = white
            )
        }
    }
}