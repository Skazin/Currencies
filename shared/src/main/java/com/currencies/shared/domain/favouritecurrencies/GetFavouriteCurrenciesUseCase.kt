package com.currencies.shared.domain.favouritecurrencies

import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


/**
 * Get list of names of favourite currencies from the data base.
 */
open class GetFavouriteCurrenciesUseCase @Inject constructor(
    private val favouriteCurrenciesRepository: FavouriteCurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : UseCase<Unit, List<String>>(ioDispatcher) {

    override suspend fun execute(parameters: Unit) = favouriteCurrenciesRepository.getFavouriteCurrencies()
}