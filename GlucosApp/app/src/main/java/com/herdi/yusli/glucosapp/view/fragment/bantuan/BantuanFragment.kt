package com.herdi.yusli.glucosapp.view.fragment.bantuan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.adapter.ListDevAdapter
import com.herdi.yusli.glucosapp.data.Dev
import com.herdi.yusli.glucosapp.databinding.FragmentBantuanBinding

class BantuanFragment : Fragment() {
    private val list = ArrayList<Dev>()
    private lateinit var listDevAdapter: ListDevAdapter

    private var _binding: FragmentBantuanBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecylerView()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBantuanBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val listDevs: ArrayList<Dev>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.nameDev)
            val dataRole = resources.getStringArray(R.array.role)
            val dataEmail = resources.getStringArray(R.array.email)
            val dataPhoto = resources.obtainTypedArray(R.array.foto)
            val listDev = ArrayList<Dev>()
            for (i in dataName.indices) {
                val dev = Dev(dataName[i],dataRole[i], dataPhoto.getResourceId(i, -1), dataEmail[i])
                listDev.add(dev)
            }
            return listDev
        }

    private fun setRecylerView() {
        listDevAdapter = ListDevAdapter(list)
        binding.apply {
            rvDev.setHasFixedSize(true)
            rvDev.layoutManager = LinearLayoutManager(activity)
            rvDev.adapter = listDevAdapter

        }
        list.addAll(listDevs)
    }



}