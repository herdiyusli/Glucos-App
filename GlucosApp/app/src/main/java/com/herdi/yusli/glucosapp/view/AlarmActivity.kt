package com.herdi.yusli.glucosapp.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.alarm.AlarmReceiver
import com.herdi.yusli.glucosapp.alarm.TimePickerFragment
import com.herdi.yusli.glucosapp.data.Alarm
import com.herdi.yusli.glucosapp.databinding.ActivityAlarmBinding
import com.herdi.yusli.glucosapp.preference.AlarmPreference
import com.herdi.yusli.glucosapp.preference.AlarmViewModelFactory
import com.herdi.yusli.glucosapp.viewModel.AlarmViewModel
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(), View.OnClickListener,
    TimePickerFragment.DialogTimeListener {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "jamAlarm")
    private var binding: ActivityAlarmBinding? = null
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var viewModel: AlarmViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        val pref = AlarmPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, AlarmViewModelFactory(pref))[AlarmViewModel::class.java]

        viewModel.getAlarm().observe(this)
        { alarm: Alarm ->
            binding?.tvRepeatingTime?.text = alarm.jamPagi
            binding?.tvRepeatingTime2?.text = alarm.jamSiang
            binding?.tvRepeatingTime3?.text = alarm.jamMalam
        }

        binding?.tvRepeatingTime?.setOnClickListener(this)
        binding?.btnSetRepeatingAlarm?.setOnClickListener(this)
        binding?.btnCancelRepeatingAlarm?.setOnClickListener(this)

        binding?.tvRepeatingTime2?.setOnClickListener(this)
        binding?.btnSetRepeatingAlarm2?.setOnClickListener(this)
        binding?.btnCancelRepeatingAlarm2?.setOnClickListener(this)

        binding?.tvRepeatingTime3?.setOnClickListener(this)
        binding?.btnSetRepeatingAlarm3?.setOnClickListener(this)
        binding?.btnCancelRepeatingAlarm3?.setOnClickListener(this)

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
                viewModel.getAlarm().observe(this)
                { alarm: Alarm ->
                    viewModel.saveAlarm(Alarm(repeatTime, alarm.jamSiang, alarm.jamMalam))
                }
            }
            R.id.btn_cancel_repeating_alarm -> alarmReceiver.cancelAlarm(this)

            R.id.tv_repeating_time2 -> {
                val timePickerFragmentRepeat = TimePickerFragment()
                timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG2)
            }
            R.id.btn_set_repeating_alarm2 -> {
                val repeatTime = binding?.tvRepeatingTime2?.text.toString()
                val repeatMessage = "Alarm Minum Obat Siang"
                alarmReceiver.setRepeatingAlarm2(this, repeatTime, repeatMessage)
                viewModel.getAlarm().observe(this)
                { alarm: Alarm ->
                    viewModel.saveAlarm(Alarm(alarm.jamPagi, repeatTime, alarm.jamMalam))
                }
            }
            R.id.btn_cancel_repeating_alarm2 -> alarmReceiver.cancelAlarm2(this)

            R.id.tv_repeating_time3 -> {
                val timePickerFragmentRepeat = TimePickerFragment()
                timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG3)
            }
            R.id.btn_set_repeating_alarm3 -> {
                val repeatTime = binding?.tvRepeatingTime3?.text.toString()
                val repeatMessage = "Alarm Minum Obat Malam"
                alarmReceiver.setRepeatingAlarm3(this, repeatTime, repeatMessage)
                viewModel.getAlarm().observe(this)
                { alarm: Alarm ->
                    viewModel.saveAlarm(Alarm(alarm.jamPagi, alarm.jamSiang, repeatTime))
                }
            }
            R.id.btn_cancel_repeating_alarm3 -> alarmReceiver.cancelAlarm3(this)
        }
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        when (tag) {
            TIME_PICKER_REPEAT_TAG -> binding?.tvRepeatingTime?.text =
                dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG2 -> binding?.tvRepeatingTime2?.text =
                dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG3 -> binding?.tvRepeatingTime3?.text =
                dateFormat.format(calendar.time)
            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val TIME_PICKER_REPEAT_TAG = "pagi"
        private const val TIME_PICKER_REPEAT_TAG2 = "siang"
        private const val TIME_PICKER_REPEAT_TAG3 = "malam"
    }


}