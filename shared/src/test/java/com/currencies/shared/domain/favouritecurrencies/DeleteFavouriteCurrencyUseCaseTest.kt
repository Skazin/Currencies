package com.currencies.shared.domain.favouritecurrencies

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
class DeleteFavouriteCurrencyUseCaseTest : BaseTest() {

    private lateinit var deleteFavouriteCurrencyUseCase: DeleteFavouriteCurrencyUseCase

    @Mock
    lateinit var favouriteCurrenciesRepository: FavouriteCurrenciesRepository

    @Before
    override fun setUp() {
        super.setUp()
        deleteFavouriteCurrencyUseCase = DeleteFavouriteCurrencyUseCase(
            favouriteCurrenciesRepository,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val favouriteCurrencyName = "BYN"

        given(favouriteCurrenciesRepository.deleteFavouriteCurrency(favouriteCurrencyName)).willReturn(Unit)

        deleteFavouriteCurrencyUseCase(favouriteCurrencyName)

        verify(favouriteCurrenciesRepository, only()).deleteFavouriteCurrency(favouriteCurrencyName)
    }
}