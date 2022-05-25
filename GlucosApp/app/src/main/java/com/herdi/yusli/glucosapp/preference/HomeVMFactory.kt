package com.herdi.yusli.glucosapp.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.view.fragment.home.HomeViewModel

class HomeVMFactory (private val pref: HomePreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}