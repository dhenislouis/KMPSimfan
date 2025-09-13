package org.kmp.simfan

import android.app.Application

class SimfanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SimfanApplication
            private set
    }
}