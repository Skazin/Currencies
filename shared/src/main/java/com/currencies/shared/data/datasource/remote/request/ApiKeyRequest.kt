package com.currencies.shared.data.datasource.remote.request

import com.currencies.shared.BuildConfig
import com.squareup.moshi.Json

data class ApiKeyRequest (
    @Json(name = "apiKey") val apiKey: String = BuildConfig.API_KEY
)