package com.herdi.yusli.glucosapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.databinding.ActivitySplashScreenBinding
import com.herdi.yusli.glucosapp.preference.AuthPreference
import com.herdi.yusli.glucosapp.preference.AuthViewModelFactory
import com.herdi.yusli.glucosapp.rensponse.LoginData
import com.herdi.yusli.glucosapp.viewModel.AuthViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val pref = AuthPreference.getInstance(dataStore)
        val authViewModel =
            ViewModelProvider(this, AuthViewModelFactory(pref))[AuthViewModel::class.java]
        authViewModel.getData().observe(
            this
        ) { loginData: LoginData ->
            if (loginData.token != "") {
                Intent(this@SplashScreenActivity, MainActivity::class.java).also {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(it)
                        finish()
                    }, 2000)
                }
            } else {
                Intent(this@SplashScreenActivity, LoginActivity::class.java).also {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(it)
                        finish()
                    }, 2000)
                }
            }
        }
    }
}