package com.example.cryptoassistant.api.cryptoprice

import retrofit2.http.GET
import retrofit2.http.Path

interface CoinLoreApiService {

    // Получить топ криптовалют
    @GET("tickers/")
    suspend fun getTopCryptos(): CryptoResponse

    // Получить конкретную криптовалюту по ID
    @GET("ticker/?id={id}")
    suspend fun getCryptoById(@Path("id") id: String): List<CryptoItem>

    // Получить глобальную статистику
    @GET("global/")
    suspend fun getGlobalStats(): List<GlobalStats>
}