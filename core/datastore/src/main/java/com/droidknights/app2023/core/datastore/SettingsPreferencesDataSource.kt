package com.droidknights.app2023.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.droidknights.app2023.core.datastore.model.SettingsData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
    }

    val settingsData = dataStore.data.map { preferences ->
        SettingsData(
            isDarkTheme = preferences[PreferencesKey.IS_DARK_THEME] ?: false
        )
    }

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_DARK_THEME] = isDarkTheme
        }
    }
}
