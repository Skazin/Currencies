package com.currencies.shared.data.datasource.database.currencies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavouriteCurrency(favouriteCurrency: FavouriteCurrenciesEntity)

    @Query("SELECT * FROM favourite_currencies")
    suspend fun getFavouriteCurrencies(): List<FavouriteCurrenciesEntity>

    @Query("""DELETE FROM favourite_currencies WHERE row_name = :currencyName""")
    suspend fun deleteFavouriteCurrency(currencyName: String?)
}