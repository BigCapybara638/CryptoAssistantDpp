package com.example.cryptoassistant.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassistant.databinding.ItemHomeCryptotopsBinding
import com.example.cryptoassistant.api.cryptoprice.CryptoItem

class HomeCryptoTopAdapter : ListAdapter<CryptoItem, HomeCryptoTopAdapter.CryptoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        // Используем правильное имя binding класса
        val binding = ItemHomeCryptotopsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CryptoViewHolder(private val binding: ItemHomeCryptotopsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crypto: CryptoItem) {
            // Используем правильные ID из вашего layout
            binding.cryptoSymbol.text = crypto.symbol
            binding.cryptoName.text = crypto.name

            // Форматируем цену
            val price = crypto.priceUsd.toDoubleOrNull() ?: 0.0
            binding.cryptoPrice.text = if (price < 1) {
                "$${String.format("%.4f", price)}"
            } else {
                "$${String.format("%.2f", price)}"
            }

            // Изменение цены за 24ч
            val change = crypto.percentChange24h.toDoubleOrNull() ?: 0.0
            val changeText = if (change >= 0) "+${String.format("%.2f", change)}%"
            else "${String.format("%.2f", change)}%"

            binding.cryptoChange.text = changeText

            // Цвет в зависимости от изменения цены
            val context = binding.root.context
            if (change >= 0) {
                binding.cryptoChange.setTextColor(
                    ContextCompat.getColor(context, android.R.color.holo_green_dark)
                )

                binding.cryptoChange.setBackgroundColor(
                    ContextCompat.getColor(context, android.R.color.holo_green_light)
                )
            } else {
                binding.cryptoChange.setTextColor(
                    ContextCompat.getColor(context, android.R.color.holo_red_dark)
                )
                binding.cryptoChange.setBackgroundColor(
                    ContextCompat.getColor(context, android.R.color.holo_red_light)
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CryptoItem>() {
            override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}