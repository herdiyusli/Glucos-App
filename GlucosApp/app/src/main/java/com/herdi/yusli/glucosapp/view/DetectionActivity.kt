package com.herdi.yusli.glucosapp.view

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import com.herdi.yusli.glucosapp.databinding.ActivityDetectionBinding


class DetectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.inputUmur.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.inputUmur.clearFocus()
                return@OnEditorActionListener false
            }
            false
        })

        binding.buttonMulaiDeteksi.setOnClickListener {
            val intent = Intent(this, HasilPosActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}