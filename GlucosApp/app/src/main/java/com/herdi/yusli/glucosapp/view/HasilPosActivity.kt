package com.herdi.yusli.glucosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.herdi.yusli.glucosapp.R

class HasilPosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasilpos)
        supportActionBar?.hide()
    }
}