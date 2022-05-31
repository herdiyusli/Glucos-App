package com.herdi.yusli.glucosapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herdi.yusli.glucosapp.preference.AuthPreference
import com.herdi.yusli.glucosapp.rensponse.LoginData
import kotlinx.coroutines.launch

class AuthViewModel(private val pref: AuthPreference)  : ViewModel()  {
    fun getData(): LiveData<LoginData> {
        return pref.getData().asLiveData()
    }

    fun saveData(loginData: LoginData) {
        viewModelScope.launch {
            pref.saveData(loginData)
        }
    }

}