package com.currencies.shared.domain.favouritecurrencies

import com.currencies.shared.BaseTest
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
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
class GetFavouriteCurrenciesUseCaseTest : BaseTest() {

    private lateinit var getFavouriteCurrenciesUseCase: GetFavouriteCurrenciesUseCase

    @Mock
    lateinit var favouriteCurrenciesRepository: FavouriteCurrenciesRepository

    @Before
    override fun setUp() {
        super.setUp()
        getFavouriteCurrenciesUseCase = GetFavouriteCurrenciesUseCase(
            favouriteCurrenciesRepository,
            testDispatcher
        )
    }

    @Test
    fun execute() = testCoroutine {
        val favouriteCurrenciesList = listOf(
            "BYN",
            "EUR",
            "USR"
        )

        given(favouriteCurrenciesRepository.getFavouriteCurrencies()).willReturn(favouriteCurrenciesList)

        val actualResponse = getFavouriteCurrenciesUseCase(Unit)
        Assert.assertEquals(favouriteCurrenciesList, actualResponse.data)

        verify(favouriteCurrenciesRepository, only()).getFavouriteCurrencies()
    }
}