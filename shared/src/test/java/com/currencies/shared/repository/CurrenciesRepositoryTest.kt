package com.currencies.shared.repository

import android.text.TextUtils
import com.currencies.shared.BaseTest
import com.currencies.shared.data.datasource.remote.CurrenciesApi
import com.currencies.shared.data.repository.CurrenciesRepository
import com.currencies.shared.data.repository.CurrenciesRepositoryImpl
import com.currencies.shared.mock.MockLatestCurrenciesResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class CurrenciesRepositoryTest : BaseTest() {

        private lateinit var currenciesRepository: CurrenciesRepository

        @Mock
        lateinit var currenciesApi: CurrenciesApi


        @Before
        override fun setUp() {
            super.setUp()
            currenciesRepository = CurrenciesRepositoryImpl(
                currenciesApi
            )
        }

        @Test
        fun `get latest rates`() = testCoroutine {
            val chosenCurrency = "BYN"

            given(currenciesApi.getLatestRates(chosenCurrency)).willReturn(
                MockLatestCurrenciesResponse.getLatestCurrenciesResponse(base = chosenCurrency))

            currenciesRepository.getLatestRates(chosenCurrency)

            verify(currenciesApi).getLatestRates(chosenCurrency)
        }

    @Test
    fun `get favourite latest rates`() = testCoroutine {
        val chosenCurrency = "BYN"
        val favouriteCurrenciesList = listOf("EUR", "USD")
        val favouriteCurrenciesListString = "EUR, USD"

        mockkStatic(TextUtils::class)
        every { TextUtils.join(",", favouriteCurrenciesList)} answers { favouriteCurrenciesListString }

        given(currenciesApi.getFavouriteRates(chosenCurrency, favouriteCurrenciesListString)).willReturn(
            MockLatestCurrenciesResponse.getLatestCurrenciesResponse(base = chosenCurrency))

        currenciesRepository.getFavouriteRates(chosenCurrency, favouriteCurrenciesList)

        verify(currenciesApi).getFavouriteRates(chosenCurrency, favouriteCurrenciesListString)
    }

}