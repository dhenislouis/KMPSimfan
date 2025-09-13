package org.kmp.simfan.viewmodel

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

    private val _products: MutableState<List<Product>> = mutableStateOf(emptyList())
    val products: State<List<Product>> = _products

    private val _selectedProduct: MutableState<Product?> = mutableStateOf(null)
    val selectedProduct: State<Product?> = _selectedProduct

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getProducts()
                if (result.isSuccess) {
                    _products.value = result.getOrNull() ?: emptyList()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to load products"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load products"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun loadProductDetail(productId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getProductDetail(productId)
                if (result.isSuccess) {
                    _selectedProduct.value = result.getOrNull()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to load product detail"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load product detail"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        loadProducts()
    }
}