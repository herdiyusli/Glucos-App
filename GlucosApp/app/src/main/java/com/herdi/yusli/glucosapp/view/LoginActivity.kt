package com.herdi.yusli.glucosapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.databinding.ActivityLoginBinding
import com.herdi.yusli.glucosapp.preference.AuthPreference
import com.herdi.yusli.glucosapp.preference.AuthViewModelFactory
import com.herdi.yusli.glucosapp.rensponse.LoginData
import com.herdi.yusli.glucosapp.viewModel.AuthViewModel
import com.herdi.yusli.glucosapp.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data login")
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val pref = AuthPreference.getInstance(dataStore)
        val mainViewModel =
            ViewModelProvider(this, AuthViewModelFactory(pref))[AuthViewModel::class.java]


        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModel.isLoading.observe(this) {
            loading(it)
        }

        binding.apply {
            loginButton.isEnabled = false
            val editTexts = listOf(emailTxt, passwordTxt)
            for (editText in editTexts) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        val email = emailTxt.text.toString().trim()
                        val password = passwordTxt.text.toString().trim()

                        loginButton.isEnabled = isValidEmail(email)
                                && isValidPass(password)
                    }


                    override fun beforeTextChanged(
                        s: CharSequence, start: Int, count: Int, after: Int
                    ) {
                    }

                    override fun afterTextChanged(
                        s: Editable
                    ) {
                    }
                })
            }
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.Logo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginButton.setOnClickListener { view ->
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()
            viewModel.requestLogin(email, password)
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            viewModel.getHasilLog().observe(this) {
                if (it == "success") {
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
                }
                if (it == "fail") {
                    Toast.makeText(
                        this,
                        "Login Gagal Username atau Password Salah",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            viewModel.getUserProfile().observe(this) { loginResponse ->
                val name = loginResponse.loginData!!.name
                val token = loginResponse.loginData.token
                Intent(this@LoginActivity, MainActivity::class.java).also {
                    mainViewModel.saveData(LoginData(name, token))
                    startActivity(it)
                    finish()
                }
            }

        }







    }

    private fun loading(isLoading: Boolean) {
        binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPass(password: CharSequence): Boolean {
        if (password.length > 5) {
            return true
        }
        return false
    }

}