package org.kmp.simfan.auth


expect fun saveString(key: String, value: String)
expect fun getString(key: String): String?
expect fun clearString(key: String)

class AuthManager {
    private val tokenKey = "auth_token"
    private val userIdKey = "user_id"
    private val userNameKey = "user_name"

    // Platform-specific storage implementation

    fun saveToken(token: String) {
        saveString(tokenKey, token)
    }

    fun getToken(): String? {
        return getString(tokenKey)
    }

    fun clearToken() {
        clearString(tokenKey)
    }

    fun saveUserInfo(id: String, name: String) {
        saveString(userIdKey, id)
        saveString(userNameKey, name)
    }

    fun getUserId(): String? {
        return getString(userIdKey)
    }

    fun getUserName(): String? {
        return getString(userNameKey)
    }

    fun clearUserInfo() {
        clearString(userIdKey)
        clearString(userNameKey)
    }

    fun isLoggedIn(): Boolean {
        return getToken() != null
    }

    fun logout() {
        clearToken()
        clearUserInfo()
    }
}