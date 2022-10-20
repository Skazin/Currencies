package com.currencies.ui.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.currencies.R
import com.currencies.ui.theme.H3
import com.currencies.ui.theme.mainGray
import com.currencies.ui.theme.white
import com.currencies.ui.util.extensions.noRippleClickable

@ExperimentalFoundationApi
@Composable
fun CurrenciesToolbar(
    currentScreen: Destinations,
    onNavigationClick: () -> Unit = {}
) {
    when (currentScreen) {
        Destinations.HOME -> HomeToolBar()
        Destinations.FAVOURITES -> FavouritesToolBar()
        Destinations.SORT -> SortToolBar(onNavigationClick = onNavigationClick)
        else -> {}
    }
}

@ExperimentalFoundationApi
@Composable
private fun HomeToolBar() {
    Box(
        modifier = Modifier
            .background(white)
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_toolbar_title),
                style = H3,
                color = mainGray,
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun FavouritesToolBar() {
    Box(
        modifier = Modifier
            .background(white)
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.favourites_screen_toolbar_title),
                style = H3,
                color = mainGray,
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun SortToolBar(
    onNavigationClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(white)
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = mainGray,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
                .align(Alignment.CenterStart)
                .noRippleClickable { onNavigationClick() }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.sort_screen_toolbar_title),
                style = H3,
                color = mainGray,
            )
        }
    }
}