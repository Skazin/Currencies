package com.currencies.shared.di

import com.currencies.shared.data.datasource.database.CurrenciesDataBase
import com.currencies.shared.data.datasource.database.currencies.FavouriteCurrenciesDao
import com.currencies.shared.data.repository.FavouriteCurrenciesRepository
import com.currencies.shared.data.repository.FavouriteCurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FavouriteCurrenciesRepositoryModule {

    @Provides
    @Singleton
    fun provideRecipesTable(currenciesDataBase: CurrenciesDataBase): FavouriteCurrenciesDao {
        return currenciesDataBase.favouriteCurrenciesDao()
    }

    @Provides
    @Singleton
    fun provideFavouriteCurrenciesRepository(
        favouriteCurrenciesDao: FavouriteCurrenciesDao
    ): FavouriteCurrenciesRepository = FavouriteCurrenciesRepositoryImpl(favouriteCurrenciesDao)

}