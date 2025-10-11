package com.example.cryptoassistant.api.crypronews

import retrofit2.http.GET

interface CoinDeskApiService {
    @GET("list?lang=EN&limit=10")
    suspend fun getCryptoNews(): CryptoNewsResponse
}