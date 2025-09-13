package org.kmp.simfan.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.SignInResponse
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class RegisterViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _registerResult: MutableState<SignInResponse?> = mutableStateOf(null)
    val registerResult: State<SignInResponse?> = _registerResult

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    fun register(name: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.signUp(name, email, phone, password)
                if (result.isSuccess) {
                    _registerResult.value = result.getOrNull()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Registration failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Registration failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearRegisterResult() {
        _registerResult.value = null
    }
}