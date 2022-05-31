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
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.databinding.ActivityRegisterBinding
import com.herdi.yusli.glucosapp.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]


        viewModel.isLoading.observe(this) {
            loading(it)
        }

        binding.apply {
            registerBtn.isEnabled = false
            val editTexts = listOf(nameTxt, emailTxt, passwordTxt)
            for (editText in editTexts) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        val nama = nameTxt.text.toString().trim()
                        val email = emailTxt.text.toString().trim()
                        val password = passwordTxt.text.toString().trim()

                        registerBtn.isEnabled = nama.isNotEmpty()
                                && isValidEmail(email)
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


        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.registerBtn.setOnClickListener { view ->
            val name = binding.nameTxt.text.toString().trim()
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            viewModel.requestRegister(name, email, password)
            viewModel.getHasilReg().observe(this) {
                if (it == "success") {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                if (it == "fail") {
                    Toast.makeText(this, "Register Gagal Email Telah Digunakan", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
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