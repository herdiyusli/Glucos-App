package com.herdi.yusli.glucosapp.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herdi.yusli.glucosapp.data.Malam
import com.herdi.yusli.glucosapp.data.Pagi
import com.herdi.yusli.glucosapp.data.Siang
import com.herdi.yusli.glucosapp.preference.HomePreference
import kotlinx.coroutines.launch

class HomeViewModel(private val pref: HomePreference) : ViewModel() {
    fun getObatPagi(): LiveData<Pagi> {
        return pref.getObatPagi().asLiveData()
    }

    fun saveObatPagi(pagi: Pagi) {
        viewModelScope.launch {
            pref.saveObatPagi(pagi)
        }
    }

    fun getObatSiang(): LiveData<Siang> {
        return pref.getObatSiang().asLiveData()
    }

    fun saveObatSiang(siang: Siang) {
        viewModelScope.launch {
            pref.saveObatSiang(siang)
        }
    }

    fun getObatMalam(): LiveData<Malam> {
        return pref.getObatMalam().asLiveData()
    }

    fun saveObatMalam(malam: Malam) {
        viewModelScope.launch {
            pref.saveObatMalam(malam)
        }
    }
}