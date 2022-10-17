package com.currencies.shared.data.datasource.database.currencies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavouriteCurrency(favouriteCurrency: FavouriteCurrenciesEntity)

    @Query("SELECT * FROM favourite_currencies")
    fun getFavouriteCurrenciesFlow(): Flow<List<FavouriteCurrenciesEntity>>

    @Query("SELECT * FROM favourite_currencies")
    suspend fun getFavouriteCurrencies(): List<FavouriteCurrenciesEntity>

    @Query("""DELETE FROM favourite_currencies WHERE row_id = :currencyId""")
    suspend fun deleteFavouriteCurrency(currencyId: Long?)
}