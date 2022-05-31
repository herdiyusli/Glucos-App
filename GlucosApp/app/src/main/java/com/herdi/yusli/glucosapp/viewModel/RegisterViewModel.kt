package com.herdi.yusli.glucosapp.viewModel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.herdi.yusli.glucosapp.api.ApiConfig
import com.herdi.yusli.glucosapp.rensponse.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application)  {
    private val _register = MutableLiveData<String?>()
    val register: MutableLiveData<String?> = _register

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun requestRegister(name: String, email: String, password: String) {
        _isLoading.value = true
        _register.value = null
        val client = ApiConfig.getApiService().registerRequest(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    register.value = "success"
                } else {
                    register.value = "fail"
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "Failure: ${t.message}")
            }
        })
    }

    fun getHasilReg(): MutableLiveData<String?> {
        return register
    }

}