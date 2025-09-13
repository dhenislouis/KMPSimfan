package org.kmp.simfan.presentation.product

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Product
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class ProductViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _depositProducts: MutableState<List<Product>> = mutableStateOf(emptyList())
    val depositProducts: State<List<Product>> = _depositProducts

    private val _savingsProducts: MutableState<List<Product>> = mutableStateOf(emptyList())
    val savingsProducts: State<List<Product>> = _savingsProducts

    private val _selectedProduct: MutableState<Product?> = mutableStateOf(null)
    val selectedProduct: State<Product?> = _selectedProduct

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Load deposit products
                val depositResult = repository.getProducts()
                if (depositResult.isSuccess) {
                    _depositProducts.value = depositResult.getOrNull() ?: emptyList()
                } else {
                    _errorMessage.value = depositResult.exceptionOrNull()?.message ?: "Failed to load deposit products"
                }

                // Load savings products
                val savingsResult = repository.getProducts()
                if (savingsResult.isSuccess) {
                    _savingsProducts.value = savingsResult.getOrNull() ?: emptyList()
                } else {
                    _errorMessage.value = savingsResult.exceptionOrNull()?.message ?: "Failed to load savings products"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load products"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadProductDetail(productId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.getProductDetail(productId)
                if (result.isSuccess) {
                    _selectedProduct.value = result.getOrNull()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Failed to load product detail"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load product detail"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadProducts()
    }
}