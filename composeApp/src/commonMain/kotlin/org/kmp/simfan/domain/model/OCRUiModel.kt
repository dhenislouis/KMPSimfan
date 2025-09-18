package org.kmp.simfan.domain.model

data class OCRUiModel(
    val province: String,
    val regency: String,
    val nationalIdentityNumber: String,
    val name: String,
    val placeAndDateOfBirth: String,
    val gender: String,
    val address: String,
    val rtRw: String,
    val villageSuburb: String,
    val district: String,
    val religion: String,
    val maritalStatus: String,
    val occupation: String,
    val citizenship: String,
) {
    companion object {
        val Empty = OCRUiModel(
            province = "",
            regency = "",
            nationalIdentityNumber = "",
            name = "",
            placeAndDateOfBirth = "",
            gender = "",
            address = "",
            rtRw = "",
            villageSuburb = "",
            district = "",
            religion = "",
            maritalStatus = "",
            occupation = "",
            citizenship = "",
        )
    }
}
