package com.currencies.shared.di

import android.content.Context
import com.currencies.shared.data.datasource.database.CurrenciesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCurrenciesDataBase(@ApplicationContext context: Context): CurrenciesDataBase =
        CurrenciesDataBase.buildDatabase(context)
}