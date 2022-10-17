package com.currencies.ui.home

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.currencies.ui.base.*
import com.currencies.ui.theme.mainGray
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeView(
    viewModel: HomeViewModel,
    navController: NavHostController,
    currentScreen: Destinations = Destinations.HOME,
    scaffoldState: ScaffoldState,
    systemUiController: SystemUiController,
    darkSystemIcons: Boolean,
) {
    val ratesList = viewModel.ratesList.collectAsLazyPagingItems()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkSystemIcons
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = darkSystemIcons
        )
    }


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
                    ratesList.refresh()
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
                if (ratesList.loadState.refresh is LoadState.Loading) item { CircularProgressIndicator(color = mainGray) }

                if (ratesList.loadState.refresh is LoadState.NotLoading) items(
                    items = ratesList
                ) { item ->
                    item?.let { uiRate ->
                        CurrencyCard(
                            currencyName = uiRate.currency,
                            currencyRate = uiRate.rate,
                            isFavourite = uiRate.isFavourite,
                            onFavouriteClick = { isFavourite ->
                                uiRate.isFavourite = isFavourite
                                viewModel.onFavouriteStarClick(uiRate, isFavourite, uiRate.id)
                            }
                        )
                    }
                }
            }
        }
    }
}
