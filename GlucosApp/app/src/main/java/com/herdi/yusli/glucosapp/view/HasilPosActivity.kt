package com.herdi.yusli.glucosapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.herdi.yusli.glucosapp.databinding.ActivityHasilposBinding

class HasilPosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilposBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val hasil = intent.getStringExtra(EXTRA_HASIL)!!.toFloat()
        val persentase = hasil * 100
        val formatPersen = String.format("%.2f%%", persentase)

        binding.persentaseTv.text = formatPersen

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val EXTRA_HASIL = "extra_hasil"
    }
}