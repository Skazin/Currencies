package com.currencies.shared.di

import android.content.Context
import com.currencies.shared.utils.dataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedModule {

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ) = context.dataStore

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}