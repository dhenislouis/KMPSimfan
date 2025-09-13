package org.kmp.simfan.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.BankAccount
import org.kmp.simfan.model.Profile
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class ProfileViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _profile: MutableState<Profile?> = mutableStateOf(null)
    val profile: State<Profile?> = _profile

    private val _bankAccounts: MutableState<List<BankAccount>> = mutableStateOf(emptyList())
    val bankAccounts: State<List<BankAccount>> = _bankAccounts

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    init {
        loadProfileData()
    }

    fun loadProfileData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // Load profile
                val profileResult = repository.getProfile()
                if (profileResult.isSuccess) {
                    _profile.value = profileResult.getOrNull()
                } else {
                    _error.value = profileResult.exceptionOrNull()?.message ?: "Failed to load profile"
                }

                // Load bank accounts
                val bankAccountsResult = repository.getBankAccounts()
                if (bankAccountsResult.isSuccess) {
                    _bankAccounts.value = bankAccountsResult.getOrNull() ?: emptyList()
                } else {
                    _error.value = bankAccountsResult.exceptionOrNull()?.message ?: "Failed to load bank accounts"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load profile data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addBankAccount(bankName: String, accountNumber: String, accountHolder: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.addBankAccount(bankName, accountNumber, accountHolder)
                if (result.isSuccess) {
                    // Refresh bank accounts list
                    val bankAccountsResult = repository.getBankAccounts()
                    if (bankAccountsResult.isSuccess) {
                        _bankAccounts.value = bankAccountsResult.getOrNull() ?: emptyList()
                    }
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to add bank account"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to add bank account"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setPrimaryBankAccount(bankAccountId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.setPrimaryBankAccount(bankAccountId)
                if (result.isSuccess) {
                    // Refresh bank accounts list
                    val bankAccountsResult = repository.getBankAccounts()
                    if (bankAccountsResult.isSuccess) {
                        _bankAccounts.value = bankAccountsResult.getOrNull() ?: emptyList()
                    }
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to set primary bank account"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to set primary bank account"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteBankAccount(bankAccountId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                repository.deleteBankAccount(bankAccountId)
                // Refresh bank accounts list
                val bankAccountsResult = repository.getBankAccounts()
                if (bankAccountsResult.isSuccess) {
                    _bankAccounts.value = bankAccountsResult.getOrNull() ?: emptyList()
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to delete bank account"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadProfileData()
    }
}