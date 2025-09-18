package org.kmp.simfan.screen.profile.pengajuandata.npwp

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val npwpModule = module {
    single { GetNPWPUseCase(get(), get()) }

    viewModel { NpwpViewModel(get()) }
}