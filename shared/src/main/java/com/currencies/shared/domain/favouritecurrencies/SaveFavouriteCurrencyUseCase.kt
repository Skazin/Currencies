package com.currencies.shared.domain.favouritecurrencies

import com.currencies.model.UiRate
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Save favourite currency in the data base.
 */
open class SaveFavouriteCurrencyUseCase @Inject constructor(
    private val favouriteCurrenciesRepository: FavouriteCurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<UiRate, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: UiRate) = favouriteCurrenciesRepository.saveFavouriteCurrency(parameters)
}