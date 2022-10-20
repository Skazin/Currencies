package com.currencies.shared.domain.currencies

import com.currencies.model.UiRate
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.favouritecurrencies.CheckIfCurrencyIsFavouriteUseCase
import com.currencies.shared.domain.favouritecurrencies.GetFavouriteCurrenciesUseCase
import com.currencies.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 * Getting list of [UiRate] objects.
 */
open class GetUiRateListUseCase @Inject constructor(
    private val getLatestCurrenciesRatesUseCase: GetLatestCurrenciesRatesUseCase,
    private val getLatestFavouriteCurrenciesRatesUseCase: GetLatestFavouriteCurrenciesRatesUseCase,
    private val getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase,
    private val checkIfCurrencyIsFavouriteUseCase: CheckIfCurrencyIsFavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun execute(parameters: Params): Result<List<UiRate>> {
        val rateList = mutableListOf<UiRate>()
        val favouritesList = mutableListOf<String>()
        val scope = CoroutineScope(ioDispatcher)

        if (parameters.isFavourite) {
            when (val favouriteCurrenciesNamesResult = getFavouriteCurrenciesUseCase(Unit)) {
                is Result.Success -> favouritesList.addAll(favouriteCurrenciesNamesResult.data)
                is Result.Error -> return Result.Error(
                    RuntimeException(
                        favouriteCurrenciesNamesResult.exception
                    )
                )
            }
            when (val latestFavouriteCurrenciesResult = getLatestFavouriteCurrenciesRatesUseCase(
                GetLatestFavouriteCurrenciesRatesUseCase.Params(
                    parameters.currency,
                    favouritesList
                )
            )
            ) {
                is Result.Success -> {
                    latestFavouriteCurrenciesResult.data.map { networkRate ->
                        rateList.add(
                            UiRate(
                                currency = networkRate.key,
                                rate = networkRate.value,
                                isFavourite = true
                            )
                        )
                    }
                    return Result.Success(rateList)
                }
                is Result.Error -> return Result.Error(
                    RuntimeException(
                        latestFavouriteCurrenciesResult.exception
                    )
                )
            }
        } else {
            when (val latestCurrenciesResult =
                getLatestCurrenciesRatesUseCase(parameters.currency)) {
                is Result.Success -> {
                    latestCurrenciesResult.data.map { networkRate ->
                        scope.async {
                            val isFavourite = scope.async { isFavourite(networkRate.key) }
                            rateList.add(
                                UiRate(
                                    currency = networkRate.key,
                                    rate = networkRate.value,
                                    isFavourite = isFavourite.await()
                                )
                            )
                        }
                    }.map {
                        it.await()
                    }.let {
                        return Result.Success(rateList)
                    }
                }
                is Result.Error -> return Result.Error(RuntimeException(latestCurrenciesResult.exception))
            }
        }
    }


    private suspend fun isFavourite(currency: String): Boolean {
        return when (val result = checkIfCurrencyIsFavouriteUseCase(currency)) {
            is Result.Success -> {
                result.data
            }
            else -> false
        }
    }

    data class Params(
        val currency: String,
        val isFavourite: Boolean
    )
}