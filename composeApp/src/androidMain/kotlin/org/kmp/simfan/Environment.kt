package org.kmp.simfan

import org.kmp.simfan.BuildConfig

private class AndroidAppEnvironment : AppEnvironment {
    override val baseUrl: String = BuildConfig.BASE_URL
}

actual fun getAppEnvironment(): AppEnvironment {
    return AndroidAppEnvironment()
}