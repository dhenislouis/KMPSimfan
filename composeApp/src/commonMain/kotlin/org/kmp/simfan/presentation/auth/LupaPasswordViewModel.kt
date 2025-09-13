package org.kmp.simfan.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class LupaPasswordViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _otpResult: MutableState<Boolean?> = mutableStateOf(null)
    val otpResult: State<Boolean?> = _otpResult

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    fun requestOTP(method: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Implementasi request OTP
                // Anda perlu menambahkan metode ini di repository
                _otpResult.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to request OTP"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun validateOTP(otp: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Implementasi validasi OTP
                // Anda perlu menambahkan metode ini di repository
                _otpResult.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to validate OTP"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun changePassword(newPassword: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Implementasi ubah password
                // Anda perlu menambahkan metode ini di repository
                _otpResult.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to change password"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearOTPResult() {
        _otpResult.value = null
    }
}