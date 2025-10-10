package com.example.cryptoassistant.cryptoprice

class CryptoRepository {

    private val apiService = RetrofitClient.coinLoreApiService

    // Получить топ криптовалют
    suspend fun getTopCryptos(limit: Int = 50): List<CryptoItem> {
        return try {
            println("📡 Getting top $limit cryptos from CoinLore...")
            val response = apiService.getTopCryptos()
            println("✅ Success! Received ${response.data.size} cryptos")
            response.data.take(limit)
        } catch (e: Exception) {
            println("❌ CoinLore API Error: ${e.message}")
            emptyList()
        }
    }

    // Получить глобальную статистику
    suspend fun getGlobalStats(): GlobalStats? {
        return try {
            println("📡 Getting global stats from CoinLore...")
            val response = apiService.getGlobalStats()
            response.firstOrNull()
        } catch (e: Exception) {
            println("❌ Global stats error: ${e.message}")
            null
        }
    }
}