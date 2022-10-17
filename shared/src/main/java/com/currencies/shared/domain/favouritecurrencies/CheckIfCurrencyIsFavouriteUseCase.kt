package com.currencies.shared.domain.favouritecurrencies

import com.currencies.model.UiRate
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.UseCase
import com.currencies.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CheckIfCurrencyIsFavouriteUseCase @Inject constructor(
    private val getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<String, Boolean>(ioDispatcher) {

    override suspend fun execute(parameters: String): Boolean {
        return when (val a = getFavouriteCurrenciesUseCase(Unit) ) {
            is Result.Success -> a.data.contains(UiRate(currency = parameters))
            else -> false
        }
    }
}