package com.currencies.shared.domain.currencies

import com.currencies.shared.data.repository.CurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLatestFavouriteCurrenciesRatesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<GetLatestFavouriteCurrenciesRatesUseCase.Params, Map<String, Double>>(ioDispatcher) {

    override suspend fun execute(parameters: Params): Map<String, Double> =
        currenciesRepository.getFavouriteRates(
            parameters.chosenCurrency,
            parameters.favouriteCurrencies
        )

    data class Params(
        val chosenCurrency: String,
        val favouriteCurrencies: List<String>
    )
}