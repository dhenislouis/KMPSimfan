package org.kmp.simfan.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Promotion
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class PromotionViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _promotions: MutableState<List<Promotion>> = mutableStateOf(emptyList())
    val promotions: State<List<Promotion>> = _promotions

    private val _selectedPromotion: MutableState<Promotion?> = mutableStateOf(null)
    val selectedPromotion: State<Promotion?> = _selectedPromotion

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    init {
        loadPromotions()
    }

    fun loadPromotions() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getPromotions()
                if (result.isSuccess) {
                    _promotions.value = result.getOrNull() ?: emptyList()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to load promotions"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load promotions"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectPromotion(promotion: Promotion) {
        _selectedPromotion.value = promotion
    }

    fun loadPromotionDetail(promotionId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getPromotionDetail(promotionId)
                if (result.isSuccess) {
                    _selectedPromotion.value = result.getOrNull()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to load promotion detail"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load promotion detail"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadPromotions()
    }
}