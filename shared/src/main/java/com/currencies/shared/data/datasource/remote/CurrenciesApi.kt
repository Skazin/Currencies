package com.currencies.shared.data.datasource.remote

import com.currencies.shared.data.datasource.remote.response.LatestCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("base") base: String
    ): LatestCurrenciesResponse

    @GET("latest")
    suspend fun getFavouriteRates(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): LatestCurrenciesResponse
}