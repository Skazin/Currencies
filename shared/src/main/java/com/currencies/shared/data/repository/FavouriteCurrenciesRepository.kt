package com.currencies.shared.data.repository

import com.currencies.model.UiRate
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesDao
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesEntity
import com.currencies.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavouriteCurrenciesRepository {

    suspend fun saveFavouriteCurrency(favouriteCurrency: UiRate)

    fun getFavouriteCurrenciesFlow(): Flow<Result<List<UiRate>>>

    suspend fun getFavouriteCurrencies(): List<UiRate>

    suspend fun deleteFavouriteCurrency(currencyId: Long?)
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

    override suspend fun getFavouriteCurrencies(): List<UiRate> =
        favouriteCurrenciesDao.getFavouriteCurrencies().toUiRate()


    override fun getFavouriteCurrenciesFlow(): Flow<Result<List<UiRate>>> =
        favouriteCurrenciesDao.getFavouriteCurrenciesFlow().toListUiRateFlow()


    override suspend fun deleteFavouriteCurrency(currencyId: Long?) =
        favouriteCurrenciesDao.deleteFavouriteCurrency(currencyId)

    private fun Flow<List<FavouriteCurrenciesEntity>>.toListUiRateFlow(): Flow<Result<List<UiRate>>> {
        return this.map { items ->
            Result.Success(items.toUiRate())
        }
    }

    private fun List<FavouriteCurrenciesEntity>.toUiRate(): List<UiRate> =
        this.map { it.toUiRate() }
}