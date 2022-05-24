package com.herdi.yusli.glucosapp.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.viewModel.AlarmViewModel

class AlarmViewModelFactory(private val pref: AlarmPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmViewModel::class.java)) {
            return AlarmViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}