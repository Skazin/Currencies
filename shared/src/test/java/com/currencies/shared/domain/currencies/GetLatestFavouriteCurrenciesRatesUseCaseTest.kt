package com.currencies.shared.domain.currencies

import com.currencies.shared.BaseTest
import com.currencies.shared.data.repository.CurrenciesRepository
import com.currencies.shared.result.data
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class GetLatestFavouriteCurrenciesRatesUseCaseTest : BaseTest() {

    private lateinit var getLatestFavouriteCurrenciesRatesUseCase: GetLatestFavouriteCurrenciesRatesUseCase

    @Mock
    lateinit var currenciesRepository: CurrenciesRepository

    @Before
    override fun setUp() {
        super.setUp()
        getLatestFavouriteCurrenciesRatesUseCase = GetLatestFavouriteCurrenciesRatesUseCase(
            currenciesRepository,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val chosenCurrency = "BYN"
        val favouriteCurrencies = listOf("USD", "EUR")
        val mapOfCurrencies = mapOf("USD" to 2.5, "EUR" to 2.3)
        val parameters = GetLatestFavouriteCurrenciesRatesUseCase.Params(
            chosenCurrency,
            favouriteCurrencies
        )

        given(currenciesRepository.getFavouriteRates(parameters.chosenCurrency, parameters.favouriteCurrencies)).willReturn(mapOfCurrencies)

        val actualResponse = getLatestFavouriteCurrenciesRatesUseCase(parameters)
        Assert.assertEquals(mapOfCurrencies, actualResponse.data)

        verify(currenciesRepository, only()).getFavouriteRates(parameters.chosenCurrency, parameters.favouriteCurrencies)
    }
}