package com.currencies.shared.di

import com.currencies.shared.data.datasource.remote.CurrenciesApi
import com.currencies.shared.data.repository.CurrenciesRepository
import com.currencies.shared.data.repository.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CurrenciesModule {

    @Singleton
    @Provides
    fun provideCurrenciesRepository(currenciesApi: CurrenciesApi): CurrenciesRepository =
        CurrenciesRepositoryImpl(currenciesApi)

    @Singleton
    @Provides
    fun provideCurrenciesApi(retrofit: Retrofit): CurrenciesApi =
        retrofit.create(CurrenciesApi::class.java)
}