package com.currencies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencies.model.UiRate
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase,
    private val deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase
): ViewModel() {

    abstract val currency: MutableStateFlow<String>

    fun changeCurrency(chosenCurrency: String) {
        currency.value = chosenCurrency
    }

    fun onFavouriteStarClick(
        uiRate: UiRate? = null,
        isFavourite: Boolean,
        currencyId: Long? = null
    ) {
        onFavouriteClick(uiRate, isFavourite, currencyId)
    }

    private fun onFavouriteClick(
        uiRate: UiRate? = null,
        isFavourite: Boolean,
        currencyId: Long? = null
    ) {
        viewModelScope.launch {
            if (isFavourite) deleteFavouriteCurrencyUseCase(currencyId)
            else uiRate?.let { saveFavouriteCurrencyUseCase(it) }
        }
    }
}