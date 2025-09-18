package org.kmp.simfan.domain

import kotlinx.serialization.json.Json
import org.kmp.simfan.domain.model.OCRExtractorModel
import org.kmp.simfan.domain.model.OCRUiModel
import org.kmp.simfan.repository.DocumentType
import org.kmp.simfan.repository.PromptRepository
import org.kmp.simfan.repository.SimfanRepo
import org.kmp.simfan.repository.prompt.PromptFile
import org.kmp.simfan.repository.prompt.TextRecognizeExtractor
import org.kmp.simfan.utils.cleanUp

class GetOCRUseCase(
    private val geminiRepository: SimfanRepo,
    private val promptRepository: PromptRepository
) {
    suspend operator fun invoke(image: ByteArray): Result<OCRUiModel> {
        val prompt = promptRepository.prompt(PromptFile.TextRecognizeExtractor, DocumentType.KTP)

        return try {
            geminiRepository
                .request(prompt, image)
                .map { it.cleanUp() }
                .map { Json.decodeFromString<OCRExtractorModel?>(it) }
                .map(::transformToUiModel)
                .mapCatching {
                    if (it == OCRUiModel.Empty) throw Exception("data not found")
                    it
                }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun transformToUiModel(model: OCRExtractorModel?): OCRUiModel {
        if (model == null) return OCRUiModel.Empty

        return OCRUiModel(
            province = model.province.orEmpty(),
            regency = model.regency.orEmpty(),
            nationalIdentityNumber = model.nationalIdentityNumber.orEmpty(),
            name = model.name.orEmpty(),
            placeAndDateOfBirth = model.placeAndDateOfBirth.orEmpty(),
            gender = model.gender.orEmpty(),
            address = model.address.orEmpty(),
            rtRw = model.rtRw.orEmpty(),
            villageSuburb = model.villageSuburb.orEmpty(),
            district = model.district.orEmpty(),
            religion = model.religion.orEmpty(),
            maritalStatus = model.maritalStatus.orEmpty(),
            occupation = model.occupation.orEmpty(),
            citizenship = model.citizenship.orEmpty(),
        )
    }
}