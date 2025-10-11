package com.example.cryptoassistant.api.crypronews

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
) {
    val formattedDate: String
        get() = publishedOn.toFormattedDate()

        // Computed property для относительного времени
    val relativeTime: String
        get() = publishedOn.toRelativeTime()

}

// Ресурс
data class SourceData(
    @SerializedName("NAME") val sourceName: String,
    @SerializedName("SOURCE_TYPE") val sourceType: String
)

// Категория
data class CategoryData(
    @SerializedName("NAME") val name: String
)

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000) // Умножаем на 1000, т.к. timestamp в секундах
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}

fun Long.toRelativeTime(): String {
    val currentTime = System.currentTimeMillis() / 1000
    val diff = currentTime - this

    return when {
        diff < 60 -> "только что"
        diff < 3600 -> "${diff / 60} мин. назад"
        diff < 86400 -> "${diff / 3600} ч. назад"
        diff < 2592000 -> "${diff / 86400} дн. назад"
        else -> this.toFormattedDate()
    }
}