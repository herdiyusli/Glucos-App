package com.herdi.yusli.glucosapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.databinding.ActivityDetectionBinding
import com.herdi.yusli.glucosapp.ml.NnModel
import com.herdi.yusli.glucosapp.ml.NnModel2
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer


class DetectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectionBinding

    @SuppressLint("SetTextI18n")
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
            var umur = binding.inputUmur.text.toString()
//        var input1 = umur?.text?.toString()?.toFloat() ?: "9"
            val rg = binding.radioGrup
            val radiovalue =
                (findViewById<View>(rg.checkedRadioButtonId) as? RadioButton)?.text.toString()
            val gender = if (radiovalue == "Pria") {
                1
            } else {
                0
            }
            val gejala1 = if (binding.checkBox.isChecked) {
                1
            } else {
                0
            }
            val gejala2 = if (binding.checkBox2.isChecked) {
                1
            } else {
                0
            }
            val gejala3 = if (binding.checkBox3.isChecked) {
                1
            } else {
                0
            }
            val gejala4 = if (binding.checkBox4.isChecked) {
                1
            } else {
                0
            }
            val gejala5 = if (binding.checkBox5.isChecked) {
                1
            } else {
                0
            }
            val gejala6 = if (binding.checkBox6.isChecked) {
                1
            } else {
                0
            }
            val gejala7 = if (binding.checkBox7.isChecked) {
                1
            } else {
                0
            }
            val gejala8 = if (binding.checkBox8.isChecked) {
                1
            } else {
                0
            }
            val gejala9 = if (binding.checkBox9.isChecked) {
                1
            } else {
                0
            }
            val gejala10 = if (binding.checkBox10.isChecked) {
                1
            } else {
                0
            }
            val gejala11 = if (binding.checkBox11.isChecked) {
                1
            } else {
                0
            }
            val gejala12 = if (binding.checkBox12.isChecked) {
                1
            } else {
                0
            }
            val gejala13 = if (binding.checkBox13.isChecked) {
                1
            } else {
                0
            }
            val gejala14 = if (binding.checkBox14.isChecked) {
                1
            } else {
                0
            }


            val cek = if (umur == "") {
                "99"
            } else {
                umur
            }
            val input1 = cek.toFloat()
            val input2 = gender.toFloat()
            val input3 = gejala1.toFloat()
            val input4 = gejala2.toFloat()
            val input5 = gejala3.toFloat()
            val input6 = gejala4.toFloat()
            val input7 = gejala5.toFloat()
            val input8 = gejala6.toFloat()
            val input9 = gejala7.toFloat()
            val input10 = gejala8.toFloat()
            val input11 = gejala9.toFloat()
            val input12 = gejala10.toFloat()
            val input13 = gejala11.toFloat()
            val input14 = gejala12.toFloat()
            val input15 = gejala13.toFloat()
            val input16 = gejala14.toFloat()

            val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * 16)
            byteBuffer.putFloat(input1)
            byteBuffer.putFloat(input2)
            byteBuffer.putFloat(input3)
            byteBuffer.putFloat(input4)
            byteBuffer.putFloat(input5)
            byteBuffer.putFloat(input6)
            byteBuffer.putFloat(input7)
            byteBuffer.putFloat(input8)
            byteBuffer.putFloat(input9)
            byteBuffer.putFloat(input10)
            byteBuffer.putFloat(input11)
            byteBuffer.putFloat(input12)
            byteBuffer.putFloat(input13)
            byteBuffer.putFloat(input14)
            byteBuffer.putFloat(input15)
            byteBuffer.putFloat(input16)


            val model = NnModel2.newInstance(this)

            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 16), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray
            //            val intent = Intent(this, HasilPosActivity::class.java)
            //            startActivity(intent)
            //            finish()
            binding.Pertanyaan.setText(outputFeature0[0].toString())
            binding.Pertanyaan2.setText(input1.toString() + "\n" + input2.toString() + "\n" + input3.toString() +"\n" + input4.toString() +"\n" + input5.toString() +"\n" + input6.toString() +"\n" + input7.toString() +"\n" + input8.toString() +"\n" + input9.toString() +"\n" + input10.toString() +"\n" + input11.toString() +"\n" + input12.toString() +"\n" + input13.toString() +"\n" + input14.toString() +"\n" + input15.toString() +"\n" + input16.toString())
            Toast.makeText(this, "$input2", Toast.LENGTH_LONG).show()

            model.close()

        }

// Releases model resources if no longer used.


    }
}