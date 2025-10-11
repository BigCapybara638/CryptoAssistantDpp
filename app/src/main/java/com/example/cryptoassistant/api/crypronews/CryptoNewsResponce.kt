package com.example.cryptoassistant.api.crypronews

import com.google.gson.annotations.SerializedName

// Главный ответ от API
data class CryptoNewsResponse(
    @SerializedName("Data") val data: List<CryptoNewsItem>, // "Data" с большой буквы!
    @SerializedName("Err") val error: Map<String, Any>? = null
)

// Одна новость
data class CryptoNewsItem(
    @SerializedName("ID") val id: Long, // в логах видно что ID длинные числа
    @SerializedName("PUBLISHED_ON") val publishedOn: Long,
    @SerializedName("IMAGE_URL") val imageUrl: String?,
    @SerializedName("TITLE") val title: String,
    @SerializedName("URL") val url: String,
    @SerializedName("BODY") val body: String,
    @SerializedName("SOURCE_DATA") val sourceData: SourceData?,
    @SerializedName("CATEGORY_DATA") val categoryData: List<CategoryData>? = null,
    @SerializedName("SENTIMENT") val sentiment: String? = null
)

// Ресурс
data class SourceData(
    @SerializedName("NAME") val sourceName: String,
    @SerializedName("SOURCE_TYPE") val sourceType: String
)

// Категория
data class CategoryData(
    @SerializedName("NAME") val name: String
)