package com.herdi.yusli.glucosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.alarm.AlarmReceiver
import com.herdi.yusli.glucosapp.alarm.TimePickerFragment
import com.herdi.yusli.glucosapp.databinding.ActivityAlarmBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(), View.OnClickListener, TimePickerFragment.DialogTimeListener {

    private var binding: ActivityAlarmBinding? = null
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        binding?.tvRepeatingTime?.setOnClickListener(this)
        binding?.btnSetRepeatingAlarm?.setOnClickListener(this)

        binding?.btnCancelRepeatingAlarm?.setOnClickListener(this)

        alarmReceiver = AlarmReceiver()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_repeating_time -> {
                val timePickerFragmentRepeat = TimePickerFragment()
                timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }
            R.id.btn_set_repeating_alarm -> {
                val repeatTime = binding?.tvRepeatingTime?.text.toString()
                val repeatMessage = "Alarm Minum Obat Pagi"
                alarmReceiver.setRepeatingAlarm(this, repeatTime, repeatMessage)
            }
            R.id.btn_cancel_repeating_alarm -> alarmReceiver.cancelAlarm(this)
        }
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        when (tag) {
            TIME_PICKER_REPEAT_TAG -> binding?.tvRepeatingTime?.text = dateFormat.format(calendar.time)
            else -> {
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object {
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }
}