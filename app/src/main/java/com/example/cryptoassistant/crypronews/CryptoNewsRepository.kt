package com.example.cryptoassistant.crypronews

import com.example.cryptoassistant.cryptoprice.CryptoItem
import com.example.cryptoassistant.cryptoprice.RetrofitClient

class CryptoNewsRepository {

    private val apiService = RetrofitClient.coinDeskApiService

    suspend fun getCryptoNews(limit: Int = 10): List<CryptoNewsItem> {
        return try {
            println("📡 Getting crypto news from CoinDesk...")
            val response = apiService.getCryptoNews()

            if (response.data.isNotEmpty()) {
                println("✅ Success! Received ${response.data.size} news articles")
                response.data.take(limit)
            } else {
                println("⚠️ No news articles received")
                emptyList()
            }
        } catch (e: Exception) {
            println("❌ CoinDesk API Error: ${e.message}")
            e.printStackTrace() // Добавьте это для детальной отладки
            emptyList()
        }
    }
}