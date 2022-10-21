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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.currencies.ui.base.*
import com.currencies.ui.theme.mainGray
import com.currencies.ui.theme.white
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
    needToRefresh: Boolean,
    onSortClick: () -> Unit
) {
    val rateList = viewModel.rateList.collectAsState()
    val loadingState = viewModel.loadingState.collectAsState()

    if(needToRefresh) viewModel.getUiRateList(false)


    SideEffect {
        systemUiController.setStatusBarColor(
            color = white,
            darkIcons = darkSystemIcons
        )
        systemUiController.setNavigationBarColor(
            color = white,
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
                    viewModel.getUiRateList(false)
                },
                onSortClick = onSortClick
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
                if (loadingState.value) item { CircularProgressIndicator(color = mainGray) }

                if (!loadingState.value) rateList.value.forEach { uiRate ->
                    item {
                        CurrencyCard(
                            currencyName = uiRate.currency,
                            currencyRate = uiRate.rate,
                            isFavourite = uiRate.isFavourite,
                            onFavouriteClick = { isFavourite ->
                                uiRate.isFavourite = isFavourite
                                viewModel.onFavouriteStarClick(uiRate, isFavourite, uiRate.currency)
                            }
                        )
                    }
                }
            }
        }
    }
}
