package com.currencies.ui.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.currencies.ui.favourites.FavouritesView
import com.currencies.ui.home.HomeView
import com.currencies.ui.splash.SplashView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

enum class Destinations(val destinationName: String) {
    SPLASH("splash"),
    HOME("home"),
    FAVOURITES("favourites")
}

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@InternalCoroutinesApi
@Composable
fun NavGraph(
    startDestination: String = Destinations.SPLASH.destinationName
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Destinations.SPLASH.destinationName
        ) {
            SplashView(
                navController = navController
            )
        }
        composable(
            route = Destinations.HOME.destinationName

        ) {
            HomeView(
                viewModel = hiltViewModel(),
                navController = navController,
                scaffoldState = scaffoldState,
                systemUiController = systemUiController,
                darkSystemIcons = useDarkIcons
            )
        }
        composable(
            route = Destinations.FAVOURITES.destinationName

        ) {
            FavouritesView(
                viewModel = hiltViewModel(),
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}