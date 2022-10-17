package com.currencies.shared.domain.currencies

import com.currencies.shared.data.repository.CurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLatestCurrenciesRatesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<String, Map<String, Double>>(ioDispatcher) {

    override suspend fun execute(parameters: String): Map<String, Double> = currenciesRepository.getLatestRates(parameters)
}