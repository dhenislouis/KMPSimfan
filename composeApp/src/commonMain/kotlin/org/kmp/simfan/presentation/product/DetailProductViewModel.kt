package org.kmp.simfan.presentation.product

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.BPR
import org.kmp.simfan.model.Product
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class DetailProductViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _product: MutableState<Product?> = mutableStateOf(null)
    val product: State<Product?> = _product

    private val _bpr: MutableState<BPR?> = mutableStateOf(null)
    val bpr: State<BPR?> = _bpr

    private val _relatedProducts: MutableState<List<Product>> = mutableStateOf(emptyList())
    val relatedProducts: State<List<Product>> = _relatedProducts

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    fun loadProductDetail(productId: UInt) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Load product detail
                val productResult = repository.getProductDetail(productId)
                if (productResult.isSuccess) {
                    val product = productResult.getOrNull()
                    _product.value = product

                    // Load BPR detail
                    product?.let { productData ->
                        // Gunakan bprName yang ada di model Product
                        val bprResult = repository.getBPRByName(productData.bprName)
                        if (bprResult.isSuccess) {
                            _bpr.value = bprResult.getOrNull()
                        } else {
                            _errorMessage.value = bprResult.exceptionOrNull()?.message ?: "Failed to load BPR detail"
                        }
                    }

                    // Load related products
                    val relatedResult = repository.getProducts()
                    if (relatedResult.isSuccess) {
                        _relatedProducts.value = relatedResult.getOrNull()
                            ?.filter { it.id != productId }
                            ?.take(3) ?: emptyList()
                    }
                } else {
                    _errorMessage.value = productResult.exceptionOrNull()?.message ?: "Failed to load product detail"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load product detail"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun applyDeposit(productId: UInt, amount: Double, aro: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.applyDeposit(productId, amount, 1, aro)
                if (result.isSuccess) {
                    // Navigate to next screen
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Failed to apply deposit"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to apply deposit"
            } finally {
                _isLoading.value = false
            }
        }
    }
}