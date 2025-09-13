package org.kmp.simfan.auth

import android.content.Context
import org.kmp.simfan.SimfanApplication
import androidx.core.content.edit

actual fun saveString(key: String, value: String) {
    val context = SimfanApplication.instance
    val prefs = context.getSharedPreferences("simfan_prefs", Context.MODE_PRIVATE)
    prefs.edit { putString(key, value) }
}

actual fun getString(key: String): String? {
    val context = SimfanApplication.instance
    val prefs = context.getSharedPreferences("simfan_prefs", Context.MODE_PRIVATE)
    return prefs.getString(key, null)
}

actual fun clearString(key: String) {
    val context = SimfanApplication.instance
    val prefs = context.getSharedPreferences("simfan_prefs", Context.MODE_PRIVATE)
    prefs.edit { remove(key) }
}