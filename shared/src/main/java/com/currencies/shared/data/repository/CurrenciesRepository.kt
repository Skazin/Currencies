package com.currencies.shared.data.repository

import com.currencies.shared.data.datasource.remote.CurrenciesApi

/**
* A single source point to work with currencies.
*/
interface CurrenciesRepository {

    /**
     * Get latest currencies rates
     *
     * @return list of currencies rates
     */
    suspend fun getLatestRates(chosenCurrency: String): Map<String, Double>
}

class CurrenciesRepositoryImpl(
    private val currenciesApi: CurrenciesApi
) : CurrenciesRepository {

    override suspend fun getLatestRates(chosenCurrency: String): Map<String, Double> = currenciesApi.checkPhoneNumber(chosenCurrency).rates
}