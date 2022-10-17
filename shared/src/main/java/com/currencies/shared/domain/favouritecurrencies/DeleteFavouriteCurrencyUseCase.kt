package com.currencies.shared.domain.favouritecurrencies

import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteFavouriteCurrencyUseCase @Inject constructor(
    private val favouriteCurrenciesRepository: FavouriteCurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Long?, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Long?) = favouriteCurrenciesRepository.deleteFavouriteCurrency(parameters)
}