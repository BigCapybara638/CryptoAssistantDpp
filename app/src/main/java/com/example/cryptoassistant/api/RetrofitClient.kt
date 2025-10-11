package com.example.cryptoassistant.api

import com.example.cryptoassistant.api.crypronews.CoinDeskApiService
import com.example.cryptoassistant.api.cryptoprice.CoinLoreApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // CoinLore API base URL
    private const val COINLORE_BASE_URL = "https://api.coinlore.net/api/"

    // Второй API (например, CoinGecko)
    private const val COINDESK_BASE_URL = "https://data-api.coindesk.com/news/v1/article/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // Первый Retrofit для CoinLore
    private val coinLoreRetrofit = Retrofit.Builder()
        .baseUrl(COINLORE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Второй Retrofit для CoinDesk
    private val coinDeskRetrofit = Retrofit.Builder()
        .baseUrl(COINDESK_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Первый API сервис
    val coinLoreApiService: CoinLoreApiService by lazy {
        coinLoreRetrofit.create(CoinLoreApiService::class.java)
    }

    // Второй API сервис
    val coinDeskApiService: CoinDeskApiService by lazy {
        coinDeskRetrofit.create(CoinDeskApiService::class.java)
    }
}