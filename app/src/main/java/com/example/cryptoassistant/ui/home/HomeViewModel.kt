package com.example.cryptoassistant.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoassistant.cryptoprice.CryptoItem
import com.example.cryptoassistant.cryptoprice.CryptoRepository
import com.example.cryptoassistant.cryptoprice.GlobalStats
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = CryptoRepository()

    private val _cryptos = MutableStateFlow<List<CryptoItem>>(emptyList())
    val cryptos: StateFlow<List<CryptoItem>> = _cryptos

    private val _globalStats = MutableStateFlow<GlobalStats?>(null)
    val globalStats: StateFlow<GlobalStats?> = _globalStats

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Загружаем криптовалюты и статистику параллельно
                val cryptosDeferred = async { repository.getTopCryptos(20) }
                val statsDeferred = async { repository.getGlobalStats() }

                _cryptos.value = cryptosDeferred.await()
                _globalStats.value = statsDeferred.await()

                println("✅ Loaded ${_cryptos.value.size} cryptos")

            } catch (e: Exception) {
                _error.value = "Ошибка загрузки: ${e.message}"
                println("❌ Loading error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}