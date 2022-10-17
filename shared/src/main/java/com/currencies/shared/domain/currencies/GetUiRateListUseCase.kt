package com.currencies.shared.domain.currencies

import com.currencies.model.UiRate
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.favouritecurrencies.CheckIfCurrencyIsFavouriteUseCase
import com.currencies.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class GetUiRateListUseCase @Inject constructor(
    private val getLatestCurrenciesRatesUseCase: GetLatestCurrenciesRatesUseCase,
    private val checkIfCurrencyIsFavouriteUseCase: CheckIfCurrencyIsFavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun execute(parameters: String): Result<List<UiRate>> {
        val rateList = mutableListOf<UiRate>()
        val scope = CoroutineScope(ioDispatcher)

        when (val latestCurrenciesResult = getLatestCurrenciesRatesUseCase(parameters)) {
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

    private suspend fun isFavourite(currency: String): Boolean {
        return when (val result = checkIfCurrencyIsFavouriteUseCase(currency)) {
            is Result.Success -> {
                result.data
            }
            else -> false
        }
    }
}