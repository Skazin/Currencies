package com.currencies.shared.domain.currencies

import com.currencies.android_test_shared.mock.MockUiRate
import com.currencies.model.UiRate
import com.currencies.shared.BaseTest
import com.currencies.shared.domain.favouritecurrencies.CheckIfCurrencyIsFavouriteUseCase
import com.currencies.shared.domain.favouritecurrencies.GetFavouriteCurrenciesUseCase
import com.currencies.shared.result.Result
import com.currencies.shared.result.data
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class GetUiRateListUseCaseTest : BaseTest() {

    private lateinit var getUiRateListUseCase: GetUiRateListUseCase

    @Mock
    lateinit var getLatestCurrenciesRatesUseCase: GetLatestCurrenciesRatesUseCase

    @Mock
    lateinit var getLatestFavouriteCurrenciesRatesUseCase: GetLatestFavouriteCurrenciesRatesUseCase

    @Mock
    lateinit var getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase

    @Mock
    lateinit var checkIfCurrencyIsFavouriteUseCase: CheckIfCurrencyIsFavouriteUseCase

    @Before
    override fun setUp() {
        super.setUp()
        getUiRateListUseCase = GetUiRateListUseCase(
            getLatestCurrenciesRatesUseCase = getLatestCurrenciesRatesUseCase,
            getLatestFavouriteCurrenciesRatesUseCase = getLatestFavouriteCurrenciesRatesUseCase,
            getFavouriteCurrenciesUseCase = getFavouriteCurrenciesUseCase,
            checkIfCurrencyIsFavouriteUseCase = checkIfCurrencyIsFavouriteUseCase,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val chosenCurrency = "BYN"
        val euroCurrency = "EUR"
        val dollarCurrency = "USD"
        val isFavourite = false
        val mapOfCurrencies = mapOf("USD" to 2.5, "EUR" to 2.3, "BYN" to 1.0)
        val listOfUiRates = mutableListOf<UiRate>()

        mapOfCurrencies.forEach { currency ->
            listOfUiRates.add(MockUiRate.getUiRate(
                currency = currency.key,
                rate = currency.value,
                isFavourite = false
            ))
            }


        val getUiRateListUseCaseParameters = GetUiRateListUseCase.Params(
            chosenCurrency,
            isFavourite
        )

        given(getLatestCurrenciesRatesUseCase(getUiRateListUseCaseParameters.currency)).willReturn(Result.Success(mapOfCurrencies))
        given(checkIfCurrencyIsFavouriteUseCase(chosenCurrency)).willReturn(Result.Success(false))
        given(checkIfCurrencyIsFavouriteUseCase(euroCurrency)).willReturn(Result.Success(false))
        given(checkIfCurrencyIsFavouriteUseCase(dollarCurrency)).willReturn(Result.Success(false))

        val actualResponse = getUiRateListUseCase.execute(getUiRateListUseCaseParameters)
        Assert.assertEquals(listOfUiRates, actualResponse.data)

        verify(getLatestCurrenciesRatesUseCase).invoke(getUiRateListUseCaseParameters.currency)
        verify(checkIfCurrencyIsFavouriteUseCase).invoke(chosenCurrency)
        verify(checkIfCurrencyIsFavouriteUseCase).invoke(euroCurrency)
        verify(checkIfCurrencyIsFavouriteUseCase).invoke(dollarCurrency)
        verifyZeroInteractions(getLatestFavouriteCurrenciesRatesUseCase)
        verifyZeroInteractions(getFavouriteCurrenciesUseCase)

    }

    @Test
    fun `execute for favourites`() = testCoroutine {
        val chosenCurrency = "BYN"
        val euroCurrency = "EUR"
        val dollarCurrency = "USD"
        val favouriteCurrenciesList = listOf(
            chosenCurrency,
            euroCurrency,
            dollarCurrency
        )
        val isFavourite = true
        val mapOfCurrencies = mapOf("USD" to 2.5, "EUR" to 2.3, "BYN" to 1.0)
        val listOfUiRates = mutableListOf<UiRate>()

        mapOfCurrencies.forEach { currency ->
            listOfUiRates.add(MockUiRate.getUiRate(
                currency = currency.key,
                rate = currency.value,
                isFavourite = true
            ))
        }


        val getUiRateListUseCaseParameters = GetUiRateListUseCase.Params(
            chosenCurrency,
            isFavourite
        )

        val getLatestFavouriteCurrenciesRatesUseCaseParameters = GetLatestFavouriteCurrenciesRatesUseCase.Params(
            chosenCurrency,
            favouriteCurrenciesList
        )

        given(getFavouriteCurrenciesUseCase(Unit)).willReturn(Result.Success(favouriteCurrenciesList))
        given(getLatestFavouriteCurrenciesRatesUseCase(getLatestFavouriteCurrenciesRatesUseCaseParameters)).willReturn(Result.Success(mapOfCurrencies))

        val actualResponse = getUiRateListUseCase.execute(getUiRateListUseCaseParameters)
        Assert.assertEquals(listOfUiRates, actualResponse.data)

        verify(getFavouriteCurrenciesUseCase).invoke(Unit)
        verify(getLatestFavouriteCurrenciesRatesUseCase).invoke(getLatestFavouriteCurrenciesRatesUseCaseParameters)
        verifyZeroInteractions(checkIfCurrencyIsFavouriteUseCase)

    }
}