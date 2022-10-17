package com.currencies.shared.data.datasource.remote.response

import com.squareup.moshi.Json

data class LatestCurrenciesResponse (
    @Json(name = "success") val success: Boolean,
    @Json(name = "timestamp") val timestamp: String,
    @Json(name = "base") val base: String,
    @Json(name = "date") val date: String,
    @Json(name = "rates") val rates: Map<String, Double>
)