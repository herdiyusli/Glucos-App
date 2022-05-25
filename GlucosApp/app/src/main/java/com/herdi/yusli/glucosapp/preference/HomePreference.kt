package com.herdi.yusli.glucosapp.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.herdi.yusli.glucosapp.data.Malam
import com.herdi.yusli.glucosapp.data.Pagi
import com.herdi.yusli.glucosapp.data.Siang
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomePreference(private val dataStore: DataStore<Preferences>) {


    private val _createPagi = intPreferencesKey("hPAGI")
    private val _createSiang = intPreferencesKey("hSIANG")
    private val _createMalam = intPreferencesKey("hMALAM")

    private val _sPagi = booleanPreferencesKey("sPagi")
    private val _sSiang = booleanPreferencesKey("sSIANG")
    private val _sMalam = booleanPreferencesKey("sMALAM")


    fun getObatPagi(): Flow<Pagi> {
        return dataStore.data.map { preferences ->
            Pagi(
                preferences[_createPagi] ?: 0,
                preferences[_sPagi] ?: false,
            )
        }
    }

    fun getObatSiang(): Flow<Siang> {
        return dataStore.data.map { preferences ->
            Siang(
                preferences[_createSiang] ?: 0,
                preferences[_sSiang] ?: false,
            )
        }
    }

    fun getObatMalam(): Flow<Malam> {
        return dataStore.data.map { preferences ->
            Malam(
                preferences[_createMalam] ?: 0,
                preferences[_sMalam] ?: false,
            )
        }
    }


    suspend fun saveObatPagi(pagi: Pagi) {
        dataStore.edit { preferences ->
            preferences[_createPagi] = pagi.createdAt
            preferences[_sPagi] = pagi.Status
        }
    }

    suspend fun saveObatSiang(siang: Siang) {
        dataStore.edit { preferences ->
            preferences[_createSiang] = siang.createdAt
            preferences[_sSiang] = siang.Status
        }
    }

    suspend fun saveObatMalam(malam: Malam) {
        dataStore.edit { preferences ->
            preferences[_createMalam] = malam.createdAt
            preferences[_sMalam] = malam.Status
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HomePreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): HomePreference {
            return INSTANCE ?: synchronized(this) {
                val instance = HomePreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}