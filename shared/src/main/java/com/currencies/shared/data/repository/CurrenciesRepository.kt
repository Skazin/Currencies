package com.currencies.shared.data.repository

import android.text.TextUtils
import com.currencies.shared.data.datasource.remote.CurrenciesApi

/**
* A single source point to work with currencies.
*/
interface CurrenciesRepository {

    /**
     * Get latest currencies rates
     *
     * @return map of currencies rates
     */
    suspend fun getLatestRates(chosenCurrency: String): Map<String, Double>

    /**
     * Get favourite currencies rates
     *
     * @return map of currencies rates
     */
    suspend fun getFavouriteRates(chosenCurrency: String, favouriteCurrencies: List<String>): Map<String, Double>
}

class CurrenciesRepositoryImpl(
    private val currenciesApi: CurrenciesApi
) : CurrenciesRepository {

    override suspend fun getLatestRates(chosenCurrency: String): Map<String, Double> = currenciesApi.getLatestRates(chosenCurrency).rates

    override suspend fun getFavouriteRates(chosenCurrency: String, favouriteCurrencies: List<String>): Map<String, Double> {
        val favouriteCurrenciesNames = TextUtils.join(",", favouriteCurrencies)
        return currenciesApi.getFavouriteRates(chosenCurrency, favouriteCurrenciesNames).rates
    }
}