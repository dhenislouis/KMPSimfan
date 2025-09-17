package org.kmp.simfan

interface AppEnvironment {
    val baseUrl: String
}

expect fun getAppEnvironment(): AppEnvironment