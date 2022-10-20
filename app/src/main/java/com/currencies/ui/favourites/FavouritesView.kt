package com.currencies.ui.favourites

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.currencies.ui.base.*
import com.currencies.ui.theme.mainGray
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
    scaffoldState: ScaffoldState,
    needToRefresh: Boolean,
    onSortClick: () -> Unit
) {
    val rateList = viewModel.rateList.collectAsState()
    val loadingState = viewModel.loadingState.collectAsState()

    if(needToRefresh) viewModel.getUiRateList(true)

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
                    viewModel.getUiRateList(true)
                },
                onSortClick = onSortClick
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 42.dp)
            ) {

                if (loadingState.value) item { CircularProgressIndicator(color = mainGray) }
                if (!loadingState.value) items(rateList.value) { item ->
                    CurrencyCard(
                        currencyName = item.currency,
                        currencyRate = item.rate,
                        isFavourite = item.isFavourite,
                        onFavouriteClick = { isFavourite ->
                            viewModel.onFavouriteStarClick(item, isFavourite, item.currency)
                        }
                    )
                }
            }
        }
    }
}