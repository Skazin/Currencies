package com.currencies.shared.di

import com.currencies.shared.support.navigationmanager.SortSettingsManager
import com.currencies.shared.support.navigationmanager.SortSettingsManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SupportModule {

    @Singleton
    @Provides
    fun provideSortSettingsManager(): SortSettingsManager = SortSettingsManagerImpl()
}