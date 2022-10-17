package com.currencies.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.currencies.model.UiRate
import com.currencies.shared.domain.currencies.GetUiRateListUseCase
import com.currencies.shared.domain.favouritecurrencies.DeleteFavouriteCurrencyUseCase
import com.currencies.shared.domain.favouritecurrencies.SaveFavouriteCurrencyUseCase
import com.currencies.ui.base.BaseViewModel
import com.currencies.ui.base.paging.INITIAL_LOAD_SIZE
import com.currencies.ui.base.paging.PAGE_SIZE
import com.currencies.ui.base.paging.RatesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUiRateListUseCase: GetUiRateListUseCase,
    saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase
) : BaseViewModel(
    saveFavouriteCurrencyUseCase = saveFavouriteCurrencyUseCase,
    deleteFavouriteCurrencyUseCase = deleteFavouriteCurrencyUseCase
) {

    override val currency = MutableStateFlow("BYN")

    var ratesList: Flow<PagingData<UiRate>> = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_LOAD_SIZE,
            enablePlaceholders = false
        )
    ) {
        RatesSource(
            currency = currency.value,
            getUiRateListUseCase = getUiRateListUseCase
        )
    }.flow.cachedIn(viewModelScope)
}