package com.herdi.yusli.glucosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.herdi.yusli.glucosapp.R

class HasilNegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_neg)
        supportActionBar?.hide()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}