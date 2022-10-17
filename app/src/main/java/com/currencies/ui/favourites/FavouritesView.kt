package com.currencies.ui.favourites

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.currencies.ui.base.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun FavouritesView(
    viewModel: FavouritesViewModel,
    navController: NavHostController,
    currentScreen: Destinations = Destinations.FAVOURITES,
    scaffoldState: ScaffoldState
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CurrenciesToolbar(
                currentScreen = currentScreen
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) {
        Column {
            SettingsCard(
                onCurrencySelect = {
                    viewModel.changeCurrency(it)
                }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 42.dp)
            ) {
                item {
                    AnimatedVisibility(visible = state.isEmpty) {
                        EmptyState()
                    }
                }

                items(state.favouriteCurrencies.distinct()) { uiRate ->
                    CurrencyCard(
                        currencyName = uiRate.currency,
                        currencyRate = uiRate.rate,
                        isFavourite = uiRate.isFavourite,
                        onFavouriteClick = { isFavourite ->
                            viewModel.onFavouriteStarClick(uiRate, isFavourite, uiRate.id)
                        }
                    )
                }
            }
        }
    }
}