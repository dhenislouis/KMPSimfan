package org.kmp.simfan

import android.app.Application
import org.kmp.simfan.di.appModule
import org.kmp.simfan.screen.profile.pengajuandata.npwp.npwpModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SimfanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@SimfanApplication)
            modules(
                appModule,
                npwpModule
            )
        }
    }

    companion object {
        lateinit var instance: SimfanApplication
            private set
    }
}