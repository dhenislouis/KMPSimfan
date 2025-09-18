package org.kmp.simfan.screen.profile.pengajuandata.npwp

interface OcrApi {
    suspend fun ocrNpwp(image: ByteArray): OCRUiModel
}
