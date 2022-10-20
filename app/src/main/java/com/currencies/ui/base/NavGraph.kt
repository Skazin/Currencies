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
import androidx.paging.ExperimentalPagingApi
import com.currencies.ui.favourites.FavouritesView
import com.currencies.ui.home.HomeView
import com.currencies.ui.sort.SortView
import com.currencies.ui.splash.SplashView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

enum class Destinations(val destinationName: String) {
    SPLASH("splash"),
    HOME("home"),
    FAVOURITES("favourites"),
    SORT("sort")
}

enum class NavArguments(val argName: String) {
    NEED_TO_REFRESH("needToRefresh")
}

@ExperimentalPagingApi
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
            val needToRefresh = it.arguments?.getBoolean(NavArguments.NEED_TO_REFRESH.argName)
            HomeView(
                viewModel = hiltViewModel(),
                navController = navController,
                scaffoldState = scaffoldState,
                systemUiController = systemUiController,
                darkSystemIcons = useDarkIcons,
                needToRefresh = needToRefresh!!,
                onSortClick = { navController.navigate(Destinations.SORT.destinationName) }
            )
        }
        composable(
            route = Destinations.FAVOURITES.destinationName
        ) {
            val needToRefresh = it.arguments?.getBoolean(NavArguments.NEED_TO_REFRESH.argName)
            FavouritesView(
                viewModel = hiltViewModel(),
                navController = navController,
                scaffoldState = scaffoldState,
                needToRefresh = needToRefresh!!,
                onSortClick = { navController.navigate(Destinations.SORT.destinationName) }
            )
        }
        composable(
            route = Destinations.SORT.destinationName

        ) {
            SortView(
                viewModel = hiltViewModel(),
                navController = navController,
                scaffoldState = scaffoldState,
                onNavigationClick = { navController
                    .previousBackStackEntry?.arguments?.putBoolean(
                        NavArguments.NEED_TO_REFRESH.argName,
                        true
                    )
                    navController.popBackStack() }
            )
        }
    }
}