package com.herdi.yusli.glucosapp.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import com.herdi.yusli.glucosapp.data.Alarm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AlarmPreference(private val dataStore: DataStore<Preferences>) {


    private val _pagi = stringPreferencesKey("PAGI")
    private val _siang = stringPreferencesKey("SIANG")
    private val _malam = stringPreferencesKey("MALAM")




    fun getAlarm(): Flow<Alarm> {
        return dataStore.data.map { preferences ->
            Alarm(
                preferences[_pagi] ?: "",
                preferences[_siang] ?: "",
                preferences[_malam] ?: ""
            )

        }

    }



    suspend fun saveAlarm(alarm: Alarm) {
        dataStore.edit { preferences ->
            preferences[_pagi] = alarm.jamPagi
            preferences[_siang] = alarm.jamSiang
            preferences[_malam] = alarm.jamMalam
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AlarmPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): AlarmPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = AlarmPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}