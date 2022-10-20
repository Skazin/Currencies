package com.currencies.ui.favourites

import com.currencies.shared.domain.currencies.GetUiRateListUseCase
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import com.currencies.shared.support.navigationmanager.SortSettingsManager
import com.currencies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    getUiRateListUseCase: GetUiRateListUseCase,
    saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase,
    sortSettingsManager: SortSettingsManager
) : BaseViewModel(
    getUiRateListUseCase = getUiRateListUseCase,
    sortSettingsManager = sortSettingsManager,
    saveFavouriteCurrencyUseCase = saveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase = deleteFavouriteCurrencyUseCase
) {
    init {
        getUiRateList(true)
    }
}