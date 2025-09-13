package org.kmp.simfan.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Deposit
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class DepositViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _deposit: MutableState<Deposit?> = mutableStateOf(null)
    val deposit: State<Deposit?> = _deposit

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    private val _signUrl: MutableState<String?> = mutableStateOf(null)
    val signUrl: State<String?> = _signUrl

    fun applyDeposit(productId: UInt, amount: Double, tenor: Int, aro: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.applyDeposit(productId, amount, tenor, aro)
                if (result.isSuccess) {
                    _deposit.value = result.getOrNull()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to apply deposit"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to apply deposit"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun generateDepositDocument() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val depositId = _deposit.value?.id
                if (depositId != null) {
                    val result = repository.generateDepositDocument(depositId)
                    if (result.isSuccess) {
                        _deposit.value = result.getOrNull()
                    } else {
                        _error.value = result.exceptionOrNull()?.message ?: "Failed to generate document"
                    }
                } else {
                    _error.value = "No deposit found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to generate document"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun requestSignDeposit() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val depositId = _deposit.value?.id
                if (depositId != null) {
                    val result = repository.requestSignDeposit(depositId)
                    if (result.isSuccess) {
                        _signUrl.value = result.getOrNull()
                    } else {
                        _error.value = result.exceptionOrNull()?.message ?: "Failed to request sign"
                    }
                } else {
                    _error.value = "No deposit found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to request sign"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearDeposit() {
        _deposit.value = null
        _signUrl.value = null
        _error.value = null
    }
}