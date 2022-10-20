package com.currencies.shared.data.repository

import com.currencies.model.UiRate
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesDao
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A single source point to work with favourite currencies.
 */
interface FavouriteCurrenciesRepository {

    /**
     * Save favourite currency.
     *
     * @param favouriteCurrency favourite currency rate
     */
    suspend fun saveFavouriteCurrency(favouriteCurrency: UiRate)

    /**
     * Get list of names of favourite currencies.
     *
     * @return list of names of favourite currencies
     */
    suspend fun getFavouriteCurrencies(): List<String>

    /**
     * Delete favourite currency by its name.
     *
     * @param currencyName name of favourite currency
     */
    suspend fun deleteFavouriteCurrency(currencyName: String?)
}

class FavouriteCurrenciesRepositoryImpl @Inject constructor(
    private val favouriteCurrenciesDao: FavouriteCurrenciesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FavouriteCurrenciesRepository {

    override suspend fun saveFavouriteCurrency(favouriteCurrency: UiRate) {
        val favouriteCurrencyEntity = FavouriteCurrenciesEntity(
            name = favouriteCurrency.currency,
            rate = favouriteCurrency.rate
        )
        favouriteCurrenciesDao.saveFavouriteCurrency(
            withContext(ioDispatcher) {
                favouriteCurrencyEntity
            }
        )

    }

    override suspend fun getFavouriteCurrencies(): List<String> =
        favouriteCurrenciesDao.getFavouriteCurrencies().toNamesList()

    override suspend fun deleteFavouriteCurrency(currencyName: String?) =
        favouriteCurrenciesDao.deleteFavouriteCurrency(currencyName)

    private fun List<FavouriteCurrenciesEntity>.toNamesList(): List<String> =
        this.map { it.name }
}