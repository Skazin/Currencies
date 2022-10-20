package com.currencies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencies.model.UiRate
import com.currencies.shared.domain.currencies.GetUiRateListUseCase
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import com.currencies.shared.result.Result
import com.currencies.shared.support.navigationmanager.SortSettingsManager
import com.currencies.shared.support.navigationmanager.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val getUiRateListUseCase: GetUiRateListUseCase? = null,
    private val sortSettingsManager: SortSettingsManager,
    private val saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase? = null,
    private val deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase? = null
): ViewModel() {

    private val _rateList = MutableStateFlow(listOf<UiRate>())
    val rateList = _rateList.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _currency = MutableStateFlow("BYN")

    fun getUiRateList(isFavourite: Boolean) {
        viewModelScope.launch {
            _loadingState.value = true
            getUiRateListUseCase?.let {
                when (val result = it.execute(
                    GetUiRateListUseCase.Params(_currency.value, isFavourite)
                )) {
                    is Result.Success -> {
                        val ratesList = result.data.toMutableList()
                        when (sortSettingsManager.sortSettings) {
                            SortType.ALPH_ASC_RATE_ASC -> ratesList.sortWith(compareBy<UiRate> { rate -> rate.currency }.thenBy { rate -> rate.rate })
                            SortType.ALPH_ASC_RATE_DESC -> ratesList.sortWith(compareBy<UiRate> { rate -> rate.currency }.thenByDescending { rate -> rate.rate })
                            SortType.ALPH_DESC_RATE_ASC -> ratesList.sortWith(compareByDescending<UiRate> { rate -> rate.currency }.thenBy { rate -> rate.rate })
                            SortType.ALPH_DESC_RATE_DESC -> ratesList.sortWith(compareByDescending<UiRate> { rate -> rate.currency }.thenByDescending { rate -> rate.rate })
                            else -> {}
                        }
                        _rateList.value = ratesList
                        _loadingState.value = false
                    }
                    is Result.Error -> {}
                }
            }
        }
    }

    fun changeCurrency(chosenCurrency: String) {
        _currency.value = chosenCurrency
    }

    fun onFavouriteStarClick(
        uiRate: UiRate? = null,
        isFavourite: Boolean,
        currencyName: String? = null
    ) {
        onFavouriteClick(uiRate, isFavourite, currencyName)
    }

    private fun onFavouriteClick(
        uiRate: UiRate? = null,
        isFavourite: Boolean,
        currencyName: String? = null
    ) {
        viewModelScope.launch {
            if (isFavourite) deleteFavouriteCurrencyUseCase?.let { it(currencyName) }
            else uiRate?.let { uiRate -> saveFavouriteCurrencyUseCase?.let { it(uiRate) } }
        }
    }
}