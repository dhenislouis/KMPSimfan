package org.kmp.simfan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform