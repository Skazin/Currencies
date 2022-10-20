package com.currencies.shared.domain.favouritecurrencies

import com.currencies.android_test_shared.mock.MockUiRate
import com.currencies.shared.BaseTest
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class SaveFavouriteCurrencyUseCaseTest : BaseTest() {

    private lateinit var saveFavouriteCurrencyUseCase: SaveFavouriteCurrencyUseCase

    @Mock
    lateinit var favouriteCurrenciesRepository: FavouriteCurrenciesRepository

    @Before
    override fun setUp() {
        super.setUp()
        saveFavouriteCurrencyUseCase = SaveFavouriteCurrencyUseCase(
            favouriteCurrenciesRepository,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val favouriteRate = MockUiRate.getUiRate()

        given(favouriteCurrenciesRepository.saveFavouriteCurrency(favouriteRate)).willReturn(Unit)

        saveFavouriteCurrencyUseCase(favouriteRate)

        verify(favouriteCurrenciesRepository, only()).saveFavouriteCurrency(favouriteRate)
    }
}