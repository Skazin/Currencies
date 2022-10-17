package com.currencies.ui.favourites

import androidx.lifecycle.viewModelScope
import com.currencies.model.UiRate
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.GetFavouriteCurrenciesFlowUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import com.currencies.shared.result.data
import com.currencies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteCurrenciesFlowUseCase: GetFavouriteCurrenciesFlowUseCase,
    saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase
) : BaseViewModel(
    saveFavouriteCurrencyUseCase = saveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase = deleteFavouriteCurrencyUseCase
) {

    override val currency = MutableStateFlow("BYN")

    private val _state = MutableStateFlow(FavouritesViewState())
    val state = _state.asStateFlow()

    init {
        getFavouriteCurrencies()
    }

    private fun getFavouriteCurrencies() {
        viewModelScope.launch {
            getFavouriteCurrenciesFlowUseCase(Unit).collect {
                if (it.data.isNullOrEmpty()) {
                    _state.value = FavouritesViewState(isEmpty = true)
                } else {
                    _state.value = FavouritesViewState(favouriteCurrencies = it.data!!)
                }
            }
        }
    }

    data class FavouritesViewState(
        val favouriteCurrencies: List<UiRate> = emptyList(),
        val isEmpty: Boolean = false
    )
}