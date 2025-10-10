package com.example.cryptoassistant.crypronews

import com.google.gson.annotations.SerializedName

data class CryptoNewsResponse(
    @SerializedName("data") val data: List<CryptoNewsItem>,
    @SerializedName("count") val count: Int? = 0
)

// дата класс описывающий новости
data class CryptoNewsItem(
    @SerializedName("ID") val id: Int,
    @SerializedName("PUBLISHED_ON") val publishedOn: Long,
    @SerializedName("IMAGE_URL") val imageUrl: String,
    @SerializedName("TITLE") val title: String,
    @SerializedName("URL") val url: String,
    @SerializedName("BODY") val body: String,
    @SerializedName("SOURCE_DATA") val sourceData: SourceData
)

// дата класс описывающий ресурс
data class SourceData(
    @SerializedName("NAME") val sourceName: String,
    @SerializedName("SOURCE_TYPE") val sourceType: String,

    )