package com.herdi.yusli.glucosapp.view.fragment.diabetes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdi.yusli.glucosapp.R
import com.herdi.yusli.glucosapp.adapter.TipeDiabetesAdapter
import com.herdi.yusli.glucosapp.data.Diabetes
import com.herdi.yusli.glucosapp.databinding.FragmentDiabetesBinding

class DiabetesFragment : Fragment() {

    private var _binding: FragmentDiabetesBinding? = null
    private val diabetesList = ArrayList<Diabetes>()
    private lateinit var tipeDiabetesAdapter: TipeDiabetesAdapter

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
        _binding = FragmentDiabetesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecylerView() {
        tipeDiabetesAdapter = TipeDiabetesAdapter(diabetesList)
        binding.apply {
            rvDiabtes.setHasFixedSize(true)
            rvDiabtes.layoutManager = LinearLayoutManager(activity)
            rvDiabtes.adapter = tipeDiabetesAdapter
        }
        diabetesList.addAll(listDiabetes)
    }

    private val listDiabetes: ArrayList<Diabetes>
        @SuppressLint("Recycle")
        get() {
            val dataTipe = resources.getStringArray(R.array.tipeDiabet)
            val dataDesc = resources.getStringArray(R.array.descDiabet)
            val listDiabet = ArrayList<Diabetes>()
            for (i in dataTipe.indices) {
                val diabet = Diabetes(dataTipe[i],dataDesc[i])
                listDiabet.add(diabet)
            }
            return listDiabet
        }

}