package org.kmp.simfan.screen.profile.pengajuandata.npwp

//sealed class NpwpState {
//    object Idle : NpwpState()
//    object Loading : NpwpState()
//    data class Success(val data: OCRUiModel) : NpwpState()
//    data class Error(val message: String) : NpwpState()
//}


sealed class NpwpState {
    object Idle: NpwpState()
    object Loading: NpwpState()
    data class Success(val data: NPWPUiModel): NpwpState()
    data class Error(val message: String): NpwpState()
}
