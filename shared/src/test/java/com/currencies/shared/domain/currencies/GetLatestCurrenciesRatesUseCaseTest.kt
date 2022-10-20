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
class GetLatestCurrenciesRatesUseCaseTest : BaseTest() {

    private lateinit var getLatestCurrenciesRatesUseCase: GetLatestCurrenciesRatesUseCase

    @Mock
    lateinit var currenciesRepository: CurrenciesRepository

    @Before
    override fun setUp() {
        super.setUp()
        getLatestCurrenciesRatesUseCase = GetLatestCurrenciesRatesUseCase(
            currenciesRepository,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val chosenCurrency = "BYN"
        val mapOfCurrencies = mapOf("USD" to 2.5, "EUR" to 2.3)

        given(currenciesRepository.getLatestRates(chosenCurrency)).willReturn(mapOfCurrencies)

        val actualResponse = getLatestCurrenciesRatesUseCase(chosenCurrency)
        Assert.assertEquals(mapOfCurrencies, actualResponse.data)

        verify(currenciesRepository, only()).getLatestRates(chosenCurrency)
    }
}