package org.kmp.simfan.screen.profile.pengajuandata.npwp

import kotlinx.coroutines.delay

class OcrApiImpl : OcrApi {
    override suspend fun ocrNpwp(image: ByteArray): OCRUiModel {
        delay(1500)
        return OCRUiModel(
            npwpNumber = "09.876.543.2-123.000",
            confidence = 0.98f
        )
    }
}
