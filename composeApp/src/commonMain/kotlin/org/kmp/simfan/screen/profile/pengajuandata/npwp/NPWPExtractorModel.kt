package org.kmp.simfan.screen.profile.pengajuandata.npwp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NPWPExtractorModel(
    @SerialName("npwp_number") val npwpNumber: String? = null,
    @SerialName("nama") val name: String? = null,
    @SerialName("alamat") val address: String? = null,
)