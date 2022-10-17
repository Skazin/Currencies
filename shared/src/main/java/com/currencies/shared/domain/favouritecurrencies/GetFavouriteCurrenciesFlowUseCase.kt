package com.currencies.shared.domain.favouritecurrencies

import com.currencies.model.UiRate
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.di.IoDispatcher
import com.currencies.shared.domain.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetFavouriteCurrenciesFlowUseCase @Inject constructor(
    private val favouriteCurrenciesRepository: FavouriteCurrenciesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<UiRate>>(ioDispatcher) {
    override fun execute(parameters: Unit) = favouriteCurrenciesRepository.getFavouriteCurrenciesFlow()
}