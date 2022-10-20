package com.currencies.shared.domain.favouritecurrencies

import com.currencies.shared.BaseTest
import com.currencies.shared.result.Result
import com.currencies.shared.result.data
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class CheckIfCurrencyIsFavouriteUseCaseTest : BaseTest() {

    private lateinit var checkIfCurrencyIsFavouriteUseCase: CheckIfCurrencyIsFavouriteUseCase

    @Mock
    lateinit var getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase

    @Before
    override fun setUp() {
        super.setUp()
        checkIfCurrencyIsFavouriteUseCase = CheckIfCurrencyIsFavouriteUseCase(
            getFavouriteCurrenciesUseCase,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val favouriteCurrencyName = "BYN"
        val favouriteCurrenciesList = listOf(
            "BYN",
            "EUR",
            "USR"
        )

        given(getFavouriteCurrenciesUseCase(Unit)).willReturn(Result.Success(favouriteCurrenciesList))

        val actualResponse = checkIfCurrencyIsFavouriteUseCase(favouriteCurrencyName)
        Assert.assertEquals(true, actualResponse.data)


        verify(getFavouriteCurrenciesUseCase).invoke(Unit)
    }
}