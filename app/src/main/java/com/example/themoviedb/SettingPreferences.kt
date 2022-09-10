package com.example.themoviedb

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences private constructor(private val dataStore : DataStore<Preferences>) {

    private val FAVORITE = booleanPreferencesKey("isFavorite")


    fun getFavorite(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[FAVORITE] ?: false
        }
    }

    suspend fun setFavorite() {
        dataStore.edit { preferences ->
            preferences[FAVORITE] = true
        }
    }

    suspend fun unSetFavorite() {
        dataStore.edit { preferences ->
            preferences[FAVORITE] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : SettingPreferences ?= null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }
}