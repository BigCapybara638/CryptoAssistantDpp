package com.example.cryptoassistant.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.cryptoassistant.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cryptoTopRecyclerView = binding.cryptoTopRecyclerView

        val cryptoList = listOf(
            CryptoItem("BTC", "+5.2%"),
            CryptoItem("ETH", "-2.1%"),
            CryptoItem("BNB", "+1.8%"),
            CryptoItem("BNB", "+1.8%"),
            CryptoItem("BNB", "+1.8%")
        )
        // для горизонтального списка
        val spanCount = 1
        val layoutManager = GridLayoutManager(requireContext(), spanCount, GridLayoutManager.HORIZONTAL, false)
        cryptoTopRecyclerView.layoutManager = layoutManager

        cryptoTopRecyclerView.adapter = HomeCryptoTopAdapter(cryptoList)

        // для фиксации по центру
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(cryptoTopRecyclerView)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}