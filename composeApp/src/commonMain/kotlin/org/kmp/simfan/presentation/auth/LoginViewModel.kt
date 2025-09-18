package org.kmp.simfan.presentation.auth

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.FirebaseTokenRequest
import org.kmp.simfan.model.SignInResponse
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class LoginViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _loginResult = mutableStateOf<SignInResponse?>(null)
    val loginResult: State<SignInResponse?> = _loginResult

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun login(identifier: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            println("identifier: $identifier\n password:$password")

            try {
                val result = repository.signIn(identifier, password, rememberMe)
                if (result.isSuccess) {
                    _loginResult.value = result.getOrNull()
                    println("logins success $_loginResult")
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Login failed"
                    println("logins failed $_errorMessage")

                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Login failed"
                println("logins failed $_errorMessage")

            } finally {
                _isLoading.value = false
            }
        }
    }

    var googleAuthProvider: (suspend () -> FirebaseTokenRequest)? = null
    fun loginWithGoogle() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val tokenReq = googleAuthProvider?.invoke()
                    ?: throw IllegalStateException("GoogleAuthProvider not set")

                val result = repository.firebaseLogin(tokenReq.token, tokenReq.name)
                if (result.isSuccess) {
                    _loginResult.value = result.getOrNull()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Google login failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearLoginResult() {
        _loginResult.value = null
    }
}