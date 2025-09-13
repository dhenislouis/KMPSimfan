package org.kmp.simfan.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Deposit
import org.kmp.simfan.model.Product
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.model.SaldoResponse
import org.kmp.simfan.model.SimulasiDepositoResponse
import org.kmp.simfan.model.UserProfile
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class HomeViewModel : ViewModel() {
    private val _userProfile = mutableStateOf<UserProfile?>(null)
    val userProfile: State<UserProfile?> get() = _userProfile

    private val _saldo = mutableStateOf<SaldoResponse?>(null)
    val saldo: State<SaldoResponse?> get() = _saldo

    private val _promotions = mutableStateOf<List<Promotion>>(emptyList())
    val promotions: State<List<Promotion>> get() = _promotions

    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> get() = _products

    private val _deposits = mutableStateOf<List<Deposit>>(emptyList())
    val deposits: State<List<Deposit>> get() = _deposits

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage

    private val apiService = SimfanApiService()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Load user profile
                _userProfile.value = apiService.getUserProfile()

                // Load saldo
                _saldo.value = apiService.getSaldo()

                // Load promotions
                _promotions.value = apiService.getPromosiBeranda()

                // Load products
                _products.value = apiService.getProducts()

                // Load deposits
                _deposits.value = emptyList() // Ganti dengan API call jika ada

                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun calculateSimulasiDeposito(amount: Double, tenor: Int) {
        viewModelScope.launch {
            try {
                val result = apiService.getSimulasiDeposito(amount, tenor)
                // Handle success - tidak perlu mengecek isSuccess karena bukan Result<T>
                _errorMessage.value = null
            } catch (e: Exception) {
                // Handle error
                _errorMessage.value = e.message ?: "Failed to calculate"
            }
        }
    }
}