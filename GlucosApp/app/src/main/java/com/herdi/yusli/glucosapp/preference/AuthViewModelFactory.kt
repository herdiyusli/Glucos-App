package com.herdi.yusli.glucosapp.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.viewModel.AuthViewModel

class AuthViewModelFactory(private val pref: AuthPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}