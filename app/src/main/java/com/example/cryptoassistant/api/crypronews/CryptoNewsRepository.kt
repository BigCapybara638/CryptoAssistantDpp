package com.example.cryptoassistant.api.crypronews

import com.example.cryptoassistant.api.RetrofitClient

class CryptoNewsRepository {

    private val apiService = RetrofitClient.coinDeskApiService
    suspend fun getCryptoNews(limit: Int = 10): List<CryptoNewsItem> {
        return try {
            // Сначала пробуем реальный API
            val realNews = try {
                val response = apiService.getCryptoNews()
                response.data.take(limit)
            } catch (e: Exception) {
                println("🔴 Real API failed, using mock data")
                emptyList()
            }

            // Если реальные новости пустые, используем заглушку
            if (realNews.isEmpty()) {
                getMockNews(limit)
            } else {
                realNews
            }
        } catch (e: Exception) {
            println("❌ All news sources failed, using mock data")
            getMockNews(limit)
        }
    }

    private fun getMockNews(limit: Int): List<CryptoNewsItem> {
        return listOf(
            CryptoNewsItem(
                id = 1,
                publishedOn = System.currentTimeMillis() / 1000,
                imageUrl = "https://example.com/image1.jpg",
                title = "Bitcoin reaches new all-time high",
                url = "https://example.com/news/1",
                body = "Bitcoin price continues to grow...",
                sourceData = SourceData("Crypto News", "RSS")
            ),
            CryptoNewsItem(
                id = 2,
                publishedOn = System.currentTimeMillis() / 1000 - 3600,
                imageUrl = "https://example.com/image2.jpg",
                title = "Ethereum 2.0 update released",
                url = "https://example.com/news/2",
                body = "Major update for Ethereum network...",
                sourceData = SourceData("Crypto Daily", "RSS")
            ),
            CryptoNewsItem(
                id = 2,
                publishedOn = System.currentTimeMillis() / 1000 - 3600,
                imageUrl = "https://example.com/image2.jpg",
                title = "Ethereum 2.0 update released",
                url = "https://example.com/news/2",
                body = "Major update for Ethereum network...",
                sourceData = SourceData("Crypto Daily", "RSS")
            ),
            CryptoNewsItem(
                id = 2,
                publishedOn = System.currentTimeMillis() / 1000 - 3600,
                imageUrl = "https://example.com/image2.jpg",
                title = "Ethereum 2.0 update released",
                url = "https://example.com/news/2",
                body = "Major update for Ethereum network...",
                sourceData = SourceData("Crypto Daily", "RSS")
            ),
            CryptoNewsItem(
                id = 2,
                publishedOn = System.currentTimeMillis() / 1000 - 3600,
                imageUrl = "https://example.com/image2.jpg",
                title = "Ethereum 2.0 update released",
                url = "https://example.com/news/2",
                body = "Major update for Ethereum network...",
                sourceData = SourceData("Crypto Daily", "RSS")
            ),
            CryptoNewsItem(
                id = 2,
                publishedOn = System.currentTimeMillis() / 1000 - 3600,
                imageUrl = "https://example.com/image2.jpg",
                title = "Ethereum 2.0 update released",
                url = "https://example.com/news/2",
                body = "Major update for Ethereum network...",
                sourceData = SourceData("Crypto Daily", "RSS")
            )
        ).take(limit)
    }
}