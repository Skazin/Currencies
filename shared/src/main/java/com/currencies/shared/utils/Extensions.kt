package com.currencies.shared.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
The data store creating extension with name currencies
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "currencies")