package org.kmp.simfan.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kmp.simfan.auth.AuthManager
import org.kmp.simfan.model.Notification
import org.kmp.simfan.network.SimfanApiService
import org.kmp.simfan.repository.SimfanRepository

class NotificationViewModel : ViewModel() {
    private val authManager = AuthManager()
    private val repository = SimfanRepository(
        apiService = SimfanApiService { authManager.getToken() },
        authManager = authManager
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _notifications: MutableState<List<Notification>> = mutableStateOf(emptyList())
    val notifications: State<List<Notification>> = _notifications

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        loadNotifications()
    }

    fun loadNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Note: Anda perlu menambahkan metode getNotifications di repository
                // val result = repository.getNotifications()
                // if (result.isSuccess) {
                //     _notifications.value = result.getOrNull() ?: emptyList()
                // } else {
                //     _errorMessage.value = result.exceptionOrNull()?.message ?: "Failed to load notifications"
                // }
                // Sementara gunakan data dummy
                _notifications.value = listOf(
                    Notification(
                        id = 1u,
                        title = "Penarikan deposito berhasil.",
                        message = "Dana dari penempatanmu telah berhasil dicairkan sesuai tujuan yang kamu pilih.",
                        date = "2023-07-15",
                        isRead = false
                    ),
                    Notification(
                        id = 2u,
                        title = "Top up berhasil.",
                        message = "Dana top up sebesar Rp1.000.000 berhasil masuk ke akunmu.",
                        date = "2023-07-14",
                        isRead = true
                    ),
                    Notification(
                        id = 3u,
                        title = "Promo spesial 🎉",
                        message = "Dapatkan bunga lebih tinggi untuk penempatan deposito bulan ini.",
                        date = "2023-07-13",
                        isRead = true
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load notifications"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun markAsRead(notificationId: UInt) {
        viewModelScope.launch {
            try {
                // Note: Anda perlu menambahkan metode markNotificationAsRead di repository
                // repository.markNotificationAsRead(notificationId)
                // Update local state
                _notifications.value = _notifications.value.map { notification ->
                    if (notification.id == notificationId) {
                        notification.copy(isRead = true)
                    } else {
                        notification
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to mark notification as read"
            }
        }
    }

    fun refresh() {
        loadNotifications()
    }
}