package org.kmp.simfan.repository.prompt

object PromptFile

fun interface PromptAsset {
    fun name(): String
}

val PromptFile.TextRecognizeExtractor: PromptAsset
    get() = PromptAsset { "prompt_test_recognize_extractor_ktp.txt" }

val PromptFile.TextRecognizeExtractorNPWP: PromptAsset
    get() = PromptAsset { "prompt_test_recognize_extractor_npwp.txt" }