package com.currencies.shared.data.datasource.remote

import com.currencies.shared.data.datasource.remote.response.LatestCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("latest")
    suspend fun checkPhoneNumber(
        @Query("base") base: String
    ): LatestCurrenciesResponse
}