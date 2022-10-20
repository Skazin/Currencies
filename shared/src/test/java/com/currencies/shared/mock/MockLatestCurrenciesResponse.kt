package com.currencies.shared.mock

import com.currencies.shared.data.datasource.remote.response.LatestCurrenciesResponse

object MockLatestCurrenciesResponse {

    fun getLatestCurrenciesResponse(
        success: Boolean = true,
        timestamp: String = "test time",
        base: String,
        date: String = "test date",
        rates: Map<String, Double> = mapOf()
    ): LatestCurrenciesResponse = LatestCurrenciesResponse(
        success = success,
        timestamp = timestamp,
        base = base,
        date = date,
        rates = rates
    )
}