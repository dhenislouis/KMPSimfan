package org.kmp.simfan.di

import org.kmp.simfan.domain.GetOCRUseCase
import org.kmp.simfan.network.api.CameraApi
import org.kmp.simfan.repository.PromptRepository
import org.kmp.simfan.repository.PromptRepositoryImpl
import org.kmp.simfan.repository.SimfanRepo
import org.kmp.simfan.repository.SimfanRepositoryImpl
import org.kmp.simfan.screen.profile.pengajuandata.ktp.KtpViewModel
import org.kmp.simfan.screen.profile.pengajuandata.npwp.GetNPWPUseCase
import org.kmp.simfan.screen.profile.pengajuandata.npwp.NpwpViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<CameraApi> { CameraApi.create() }
    singleOf(::PromptRepositoryImpl) bind PromptRepository::class
    factoryOf(::SimfanRepositoryImpl) bind SimfanRepo::class

    factoryOf(::GetOCRUseCase)
    viewModelOf(::KtpViewModel)

    factoryOf(::GetNPWPUseCase)
    viewModelOf(::NpwpViewModel)
}