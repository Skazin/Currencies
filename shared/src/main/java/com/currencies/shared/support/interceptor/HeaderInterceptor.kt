package com.currencies.shared.support.interceptor

import com.currencies.shared.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")

        requestBuilder
            .addHeader("apiKey", BuildConfig.API_KEY)
            .addHeader("date", "2022-10-16")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}