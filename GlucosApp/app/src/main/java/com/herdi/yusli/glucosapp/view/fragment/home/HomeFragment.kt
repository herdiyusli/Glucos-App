package com.herdi.yusli.glucosapp.view.fragment.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.data.Malam
import com.herdi.yusli.glucosapp.data.Pagi
import com.herdi.yusli.glucosapp.data.Siang
import com.herdi.yusli.glucosapp.databinding.FragmentHomeBinding
import com.herdi.yusli.glucosapp.preference.HomePreference
import com.herdi.yusli.glucosapp.preference.HomeVMFactory
import com.herdi.yusli.glucosapp.view.AlarmActivity
import com.herdi.yusli.glucosapp.view.LoginActivity
import java.util.*


class HomeFragment : Fragment() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "catatan")

    private var _binding: FragmentHomeBinding? = null



    private val binding get() = _binding!!


    private var calendar = Calendar.getInstance()
    private var tanggalHariIni = calendar.get(Calendar.DAY_OF_YEAR)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val pref = HomePreference.getInstance(requireContext().dataStore)
        val homeViewModel =
            ViewModelProvider(this, HomeVMFactory(pref))[HomeViewModel::class.java]



        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.txtBtnAlarm.setOnClickListener {
            val intent = Intent(activity, AlarmActivity::class.java)
            startActivity(intent)
        }

        binding.pagiTv.setOnClickListener {
            homeViewModel.saveObatPagi(Pagi(tanggalHariIni, true))
        }

        binding.siangTv.setOnClickListener {
            homeViewModel.saveObatSiang(Siang(tanggalHariIni, true))
        }

        binding.malamTv.setOnClickListener {
            homeViewModel.saveObatMalam(Malam(tanggalHariIni, true))
        }

        binding.ResetBtn.setOnClickListener {
            val date = 0
            homeViewModel.saveObatPagi(Pagi(date, false))
            homeViewModel.saveObatSiang(Siang(date, false))
            homeViewModel.saveObatMalam(Malam(date, false))
        }

        homeViewModel.getObatPagi().observe(viewLifecycleOwner)
        { pagi: Pagi ->
            if (pagi.createdAt != tanggalHariIni) {
                val date = 0
                homeViewModel.saveObatPagi(Pagi(date, false))
            }
        }

        homeViewModel.getObatSiang().observe(viewLifecycleOwner)
        { siang: Siang ->
            if (siang.createdAt != tanggalHariIni) {
                val date = 0
                homeViewModel.saveObatSiang(Siang(date, false))
            }
        }

        homeViewModel.getObatMalam().observe(viewLifecycleOwner)
        { malam: Malam ->
            if (malam.createdAt != tanggalHariIni) {
                val date = 0
                homeViewModel.saveObatMalam(Malam(date, false))
            }
        }



        binding.apply {
            homeViewModel.getObatPagi().observe(viewLifecycleOwner)
            { pagi: Pagi ->
                if (pagi.Status) {
                    pagiTv.setBackgroundResource(R.drawable.bg_done)
                    pagiTv.text = resources.getString(R.string.pagiSudahTxt)
                } else {
                    pagiTv.setBackgroundResource(R.drawable.bg_blm)
                    pagiTv.text = resources.getString(R.string.pagiBelumTxt)
                }

            }

            homeViewModel.getObatSiang().observe(viewLifecycleOwner)
            { siang: Siang ->
                if (siang.Status) {
                    siangTv.setBackgroundResource(R.drawable.bg_done)
                    siangTv.text = resources.getString(R.string.siangSudahTxt)
                } else {
                    siangTv.setBackgroundResource(R.drawable.bg_blm)
                    siangTv.text = resources.getString(R.string.siangBelumTxt)
                }

            }

            homeViewModel.getObatMalam().observe(viewLifecycleOwner)
            { malam: Malam ->
                if (malam.Status) {
                    malamTv.setBackgroundResource(R.drawable.bg_done)
                    malamTv.text = resources.getString(R.string.malamSudahTxt)
                } else {
                    malamTv.setBackgroundResource(R.drawable.bg_blm)
                    malamTv.text = resources.getString(R.string.malamBelumTxt)

                }

            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}