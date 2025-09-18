package org.kmp.simfan.screen.profile.pengajuandata.npwp

data class NPWPUiModel(
    val npwpNumber: String,
    val name: String,
    val address: String,
) {
    companion object {
        val Empty = NPWPUiModel(
            npwpNumber = "",
            name = "",
            address = "",
        )
    }
}