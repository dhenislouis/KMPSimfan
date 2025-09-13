package org.kmp.simfan.auth

import platform.Foundation.NSUserDefaults

actual fun saveString(key: String, value: String) {
    val userDefaults = NSUserDefaults.standardUserDefaults
    userDefaults.setObject(value, key)
}

actual fun getString(key: String): String? {
    val userDefaults = NSUserDefaults.standardUserDefaults
    return userDefaults.stringForKey(key)
}

actual fun clearString(key: String) {
    val userDefaults = NSUserDefaults.standardUserDefaults
    userDefaults.removeObjectForKey(key)
}