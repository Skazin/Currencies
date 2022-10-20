package com.currencies.ui.sort

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.currencies.R
import com.currencies.ui.base.BottomNavigationBar
import com.currencies.ui.base.CurrenciesToolbar
import com.currencies.ui.base.Destinations
import com.currencies.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun SortView(
    viewModel: SortViewModel,
    navController: NavHostController,
    currentScreen: Destinations = Destinations.SORT,
    scaffoldState: ScaffoldState,
    onNavigationClick: () -> Unit
) {

    val alphabetAscChecked = viewModel.alphabetAscChecked.collectAsState()
    val alphabetDescChecked = viewModel.alphabetDescChecked.collectAsState()
    val rateAscChecked = viewModel.rateAscChecked.collectAsState()
    val rateDescChecked = viewModel.rateDescChecked.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CurrenciesToolbar(
                currentScreen = currentScreen,
                onNavigationClick = onNavigationClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) {
        Column {
            SortSettingsCard(
                alphabetAscChecked = alphabetAscChecked.value,
                alphabetDescChecked = alphabetDescChecked.value,
                rateAscChecked = rateAscChecked.value,
                rateDescChecked = rateDescChecked.value,
                onChange = { alphabetAscChecked, rateAscChecked -> viewModel.checkState(alphabetAscChecked, rateAscChecked) }
            )
        }
    }
}

@Composable
private fun SortSettingsCard(
    alphabetAscChecked: Boolean,
    alphabetDescChecked: Boolean,
    rateAscChecked: Boolean,
    rateDescChecked: Boolean,
    onChange: (Boolean, Boolean) -> Unit
) {

    val _alphabetAscChecked = remember { mutableStateOf(alphabetAscChecked) }
    val _alphabetDescChecked = remember { mutableStateOf(alphabetDescChecked) }
    val _rateAscChecked = remember { mutableStateOf(rateAscChecked) }
    val _rateDescChecked = remember { mutableStateOf(rateDescChecked) }

    Card(
        elevation = 0.dp,
        backgroundColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sort_screen_alphabet_settings_title),
                style = H5,
                color = mainGray
            )
            RadioButtonAndTextRow(
                textId = R.string.sort_screen_settings_asc,
                checked = _alphabetAscChecked.value,
                onClick = {
                    _alphabetAscChecked.value = !_alphabetAscChecked.value
                    _alphabetDescChecked.value = false
                    onChange(_alphabetAscChecked.value, _rateAscChecked.value)
                },
            )
            RadioButtonAndTextRow(
                textId = R.string.sort_screen_settings_desc,
                checked = _alphabetDescChecked.value,
                onClick = {
                    _alphabetDescChecked.value = !_alphabetDescChecked.value
                    _alphabetAscChecked.value = false
                    onChange(_alphabetAscChecked.value, _rateAscChecked.value)
                },
            )

            Text(
                text = stringResource(id = R.string.sort_screen_rates_settings_title),
                style = H5,
                color = mainGray
            )
            RadioButtonAndTextRow(
                textId = R.string.sort_screen_settings_asc,
                checked = _rateAscChecked.value,
                onClick = {
                    _rateAscChecked.value = !_rateAscChecked.value
                    _rateDescChecked.value = false
                    onChange(_alphabetAscChecked.value, _rateAscChecked.value)
                }
            )
            RadioButtonAndTextRow(
                textId = R.string.sort_screen_settings_desc,
                checked = _rateDescChecked.value,
                onClick = {
                    _rateDescChecked.value = !_rateDescChecked.value
                    _rateAscChecked.value = false
                    onChange(_alphabetAscChecked.value, _rateAscChecked.value)
                },
            )
        }
    }
}

@Composable
private fun RadioButtonAndTextRow(
    @StringRes textId: Int,
    checked: Boolean,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked,
            onClick = {
                if (!checked) {
                    onClick()
                }
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = main,
                unselectedColor = inactiveGray
            )
        )
        Text(
            text = stringResource(id = textId),
            style = T3,
            color = textGray,
            modifier = Modifier.padding(bottom = 5.dp)
        )
    }
}