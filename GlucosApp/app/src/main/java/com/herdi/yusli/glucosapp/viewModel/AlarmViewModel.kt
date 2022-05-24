package com.herdi.yusli.glucosapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herdi.yusli.glucosapp.data.Alarm
import com.herdi.yusli.glucosapp.preference.AlarmPreference
import kotlinx.coroutines.launch

class AlarmViewModel(private val pref: AlarmPreference) : ViewModel() {
    fun getAlarm(): LiveData<Alarm> {
        return pref.getAlarm().asLiveData()
    }

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            pref.saveAlarm(alarm)
        }
    }

}
