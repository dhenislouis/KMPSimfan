package org.kmp.simfan.screen.profile.pengajuandata.ktp

import org.kmp.simfan.domain.model.OCRUiModel

sealed class KtpState {
    object Idle: KtpState()
    object Loading: KtpState()
    data class Success(val data: OCRUiModel): KtpState()
    data class Error(val message: String): KtpState()
}