package com.herdi.yusli.glucosapp.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.herdi.yusli.glucosapp.rensponse.LoginData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreference(private val dataStore: DataStore<Preferences>) {


    private val TOKEN_USER = stringPreferencesKey("token_user")

    private val NAME_USER = stringPreferencesKey("name_user")

    fun getData(): Flow<LoginData> {
        return dataStore.data.map { preferences ->
            LoginData(
                preferences[NAME_USER] ?: "",
                preferences[TOKEN_USER] ?: "",
            )
        }

    }

    suspend fun saveData(loginData: LoginData) {
        dataStore.edit { preferences ->
            preferences[NAME_USER] = loginData.name
            preferences[TOKEN_USER] = loginData.token
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): AuthPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}