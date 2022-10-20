package com.currencies.ui.favourites

import com.currencies.model.UiRate
import com.currencies.shared.domain.currencies.GetUiRateListUseCase
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import com.currencies.shared.support.navigationmanager.SortSettingsManager
import com.currencies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for managing favourites view.
 *
 * @param getUiRateListUseCase for getting list of [UiRate] objects
 * @param saveFavouriteCurrencyUseCase for save favourite currency
 * @param deleteFavouriteCurrencyUseCase for delete favourite currency
 * @param sortSettingsManager for using sort
 *
 */
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