package com.currencies.shared.domain.favouritecurrencies

import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.UseCase
import com.currencies.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Checking if currency is favourite from the data base.
 */
open class CheckIfCurrencyIsFavouriteUseCase @Inject constructor(
    private val getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<String, Boolean>(ioDispatcher) {

    override suspend fun execute(parameters: String): Boolean {
        return when (val result = getFavouriteCurrenciesUseCase(Unit) ) {
            is Result.Success -> result.data.contains(parameters)
            else -> false
        }
    }
}