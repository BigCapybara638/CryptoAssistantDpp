package com.example.cryptoassistant.crypronews

import com.example.cryptoassistant.cryptoprice.CryptoResponse
import retrofit2.http.GET

interface CoinDeskApiService {
    @GET("/news/v1/article/list?lang=EN&limit=10")
    suspend fun getCryptoNews(): CryptoNewsResponse
}