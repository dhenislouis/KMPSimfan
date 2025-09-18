package org.kmp.simfan.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(appModule)
}