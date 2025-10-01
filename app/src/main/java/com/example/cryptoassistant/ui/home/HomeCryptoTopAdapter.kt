package com.example.cryptoassistant.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassistant.R

class HomeCryptoTopAdapter(private val data: List<CryptoItem>) :
    RecyclerView.Adapter<HomeCryptoTopAdapter.HomeCryptoTopViewHolder>() {

    class HomeCryptoTopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameCrypto: TextView = itemView.findViewById(R.id.nameCrupto)
        val priceDynamics: TextView = itemView.findViewById(R.id.priceDynamics)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCryptoTopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_cryptotops, parent, false)
        return HomeCryptoTopViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HomeCryptoTopViewHolder,
        position: Int
    ) {
        val item = data[position]
        holder.nameCrypto.text = item.nameCrypto
        holder.priceDynamics.text = item.priceDynamics
    }

    override fun getItemCount(): Int = data.size
}