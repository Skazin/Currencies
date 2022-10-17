package com.currencies.ui.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.currencies.R
import com.currencies.ui.theme.main
import com.currencies.ui.theme.mainGray
import com.currencies.ui.theme.white

sealed class CurrenciesMainMenu(val route: String, @DrawableRes val resourceId: Int) {
    object HomeScreen : CurrenciesMainMenu(Destinations.HOME.destinationName, R.drawable.ic_home_menu)
    object FavouritesScreen : CurrenciesMainMenu(Destinations.FAVOURITES.destinationName, R.drawable.ic_favourites_menu)
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = modifier,
        backgroundColor = white
    ) {

        Row {
            BottomNavigationItem(
                unselectedContentColor = mainGray,
                selectedContentColor = main,
                icon = {
                    Icon(
                        painter = painterResource(id = CurrenciesMainMenu.HomeScreen.resourceId),
                        contentDescription = null,
                        modifier = modifier.size(30.dp)
                    )
                },
                selected = currentRoute == CurrenciesMainMenu.HomeScreen.route,
                onClick = {
                    if (currentRoute != CurrenciesMainMenu.HomeScreen.route) {
                        navController.navigate(CurrenciesMainMenu.HomeScreen.route)
                    }
                }
            )
            BottomNavigationItem(
                unselectedContentColor = mainGray,
                selectedContentColor = main,
                icon = {
                    Icon(
                        painter = painterResource(id = CurrenciesMainMenu.FavouritesScreen.resourceId),
                        contentDescription = null,
                        modifier = modifier.size(30.dp)
                    )
                },
                selected = currentRoute == CurrenciesMainMenu.FavouritesScreen.route,
                onClick = {
                    if (currentRoute != CurrenciesMainMenu.FavouritesScreen.route) {
                        navController.navigate(CurrenciesMainMenu.FavouritesScreen.route)
                    }
                }
            )
        }
    }
}
