package org.kmp.simfan.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.model.SaldoResponse
import org.kmp.simfan.model.UserProfile
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class BerandaViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _userProfile: MutableState<UserProfile?> = mutableStateOf(null)
    val userProfile: State<UserProfile?> = _userProfile

    private val _saldo: MutableState<SaldoResponse?> = mutableStateOf(null)
    val saldo: State<SaldoResponse?> = _saldo

    private val _promotions: MutableState<List<Promotion>> = mutableStateOf(emptyList())
    val promotions: State<List<Promotion>> = _promotions

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    init {
        loadBerandaData()
    }

    fun loadBerandaData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // Load user profile
                val profileResult = repository.getUserProfile()
                if (profileResult.isSuccess) {
                    _userProfile.value = profileResult.getOrNull()
                } else {
                    _error.value = profileResult.exceptionOrNull()?.message ?: "Failed to load profile"
                }

                // Load saldo
                val saldoResult = repository.getSaldo()
                if (saldoResult.isSuccess) {
                    _saldo.value = saldoResult.getOrNull()
                } else {
                    _error.value = saldoResult.exceptionOrNull()?.message ?: "Failed to load saldo"
                }

                // Load promotions
                val promotionsResult = repository.getPromosiBeranda()
                if (promotionsResult.isSuccess) {
                    _promotions.value = promotionsResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = promotionsResult.exceptionOrNull()?.message ?: "Failed to load promotions"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load beranda data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun calculateSimulasiDeposito(amount: Double, tenor: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getSimulasiDeposito(amount, tenor)
                if (result.isSuccess) {
                    // Handle simulation result
                    // You might want to show this in a dialog or navigate to a simulation screen
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to calculate simulation"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to calculate simulation"
            }
        }
    }

    fun refresh() {
        loadBerandaData()
    }
}