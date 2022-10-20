package com.currencies.shared.repository

import com.currencies.android_test_shared.mock.MockUiRate
import com.currencies.shared.BaseTest
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesDao
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesEntity
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.data.repository.FavouriteCurrenciesRepositoryImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class FavouriteCurrenciesRepositoryTest : BaseTest() {

    private lateinit var favouriteCurrenciesRepository: FavouriteCurrenciesRepository

    @Mock
    lateinit var favouriteCurrenciesDao: FavouriteCurrenciesDao


    @Before
    override fun setUp() {
        super.setUp()
        favouriteCurrenciesRepository = FavouriteCurrenciesRepositoryImpl(
            favouriteCurrenciesDao = favouriteCurrenciesDao,
            testDispatcher
        )
    }

    @Test
    fun `save favourite currency`() = testCoroutine {
        val favouriteRate = MockUiRate.getUiRate()
        val favouriteCurrenciesEntity = FavouriteCurrenciesEntity(
            name = favouriteRate.currency,
            rate = favouriteRate.rate
        )

        given(favouriteCurrenciesDao.saveFavouriteCurrency(favouriteCurrenciesEntity))
            .willReturn(Unit)

        favouriteCurrenciesRepository.saveFavouriteCurrency(favouriteRate)

        verify(favouriteCurrenciesDao).saveFavouriteCurrency(favouriteCurrenciesEntity)
    }

    @Test
    fun `get favourite currencies`() = testCoroutine {
        val favouriteRate = MockUiRate.getUiRate()
        val favouriteCurrenciesEntityList = listOf(
            FavouriteCurrenciesEntity(
                name = favouriteRate.currency,
                rate = favouriteRate.rate
            ),
            FavouriteCurrenciesEntity(
                name = favouriteRate.currency,
                rate = favouriteRate.rate
            ),
        )

        given(favouriteCurrenciesDao.getFavouriteCurrencies())
            .willReturn(favouriteCurrenciesEntityList)

        favouriteCurrenciesRepository.getFavouriteCurrencies()

        verify(favouriteCurrenciesDao).getFavouriteCurrencies()
    }

    @Test
    fun `delete favourite currency`() = testCoroutine {
        val favouriteRate = MockUiRate.getUiRate()

        given(favouriteCurrenciesDao.deleteFavouriteCurrency(favouriteRate.currency))
            .willReturn(Unit)

        favouriteCurrenciesRepository.deleteFavouriteCurrency(favouriteRate.currency)

        verify(favouriteCurrenciesDao).deleteFavouriteCurrency(favouriteRate.currency)
    }
}