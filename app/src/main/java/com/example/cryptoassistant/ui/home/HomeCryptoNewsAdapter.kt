package com.example.cryptoassistant.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassistant.api.crypronews.CryptoNewsItem
import com.example.cryptoassistant.api.cryptoprice.CryptoItem
import com.example.cryptoassistant.databinding.ItemHomeCryptotopsBinding
import com.example.cryptoassistant.databinding.ItemHomeNewsBinding

class HomeCryptoNewsAdapter: ListAdapter<CryptoNewsItem, HomeCryptoNewsAdapter.HomeCryptoNewsViewHolder>(DIFF_CALLBACK) {

    inner class HomeCryptoNewsViewHolder(private val binding: ItemHomeNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crypto: CryptoNewsItem) {
            // Используем  ID из  layout
            binding.cryptoNewsTitle.text = crypto.title
            binding.resourceName.text = crypto.sourceData?.sourceName
            binding.cryptoNewsTime.text = crypto.relativeTime
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCryptoNewsAdapter.HomeCryptoNewsViewHolder {
        val binding = ItemHomeNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeCryptoNewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeCryptoNewsAdapter.HomeCryptoNewsViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CryptoNewsItem>() {
            override fun areItemsTheSame(oldItem: CryptoNewsItem, newItem: CryptoNewsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CryptoNewsItem, newItem: CryptoNewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}