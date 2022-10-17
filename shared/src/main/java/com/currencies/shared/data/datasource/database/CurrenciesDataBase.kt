package com.currencies.shared.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesDao
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesEntity

/**
* The [Room] database for favourite currencies.
*/
@Database(
    entities = [FavouriteCurrenciesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CurrenciesDataBase : RoomDatabase() {

    abstract fun favouriteCurrenciesDao(): FavouriteCurrenciesDao


    companion object {
        private const val databaseName = "currencies-database"

        fun buildDatabase(context: Context): CurrenciesDataBase =
            Room.databaseBuilder(context, CurrenciesDataBase::class.java, databaseName).build()
    }
}