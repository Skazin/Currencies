package com.currencies.shared.di

import com.currencies.shared.BuildConfig
import com.currencies.shared.support.constants.NetworkConstants.TIMEOUT_LIMIT_SECONDS
import com.currencies.shared.support.interceptor.HeaderInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()


    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_LIMIT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_LIMIT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_LIMIT_SECONDS, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideRetroFit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}