package org.kmp.simfan.screen.profile.pengajuandata.npwp

import kotlinx.serialization.json.Json
import org.kmp.simfan.repository.DocumentType
import org.kmp.simfan.repository.PromptRepository
import org.kmp.simfan.repository.SimfanRepo
import org.kmp.simfan.repository.prompt.PromptFile
import org.kmp.simfan.repository.prompt.TextRecognizeExtractorNPWP
import org.kmp.simfan.utils.cleanUp

class GetNPWPUseCase(
    private val geminiRepository: SimfanRepo,
    private val promptRepository: PromptRepository
) {
    suspend operator fun invoke(image: ByteArray): Result<NPWPUiModel> {
        val prompt = promptRepository.prompt(PromptFile.TextRecognizeExtractorNPWP, DocumentType.NPWP)

        return try {
            geminiRepository
                .request(prompt, image)
                .map { it.cleanUp() }
                .map { Json.decodeFromString<NPWPExtractorModel?>(it) }
                .map(::transformToUiModel)
                .mapCatching {
                    if (it == NPWPUiModel.Empty) throw Exception("data not found")
                    it
                }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun transformToUiModel(model: NPWPExtractorModel?): NPWPUiModel {
        if (model == null) return NPWPUiModel.Empty

        return NPWPUiModel(
            npwpNumber = model.npwpNumber.orEmpty(),
            name = model.name.orEmpty(),
            address = model.address.orEmpty(),
        )
    }
}