package org.kmp.simfan.screen.profile.pengajuandata.npwp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class NpwpViewModel(
//    private val ocrApi: OcrApi
//) : ViewModel() {
//    private val _state = MutableStateFlow<NpwpState>(NpwpState.Idle)
//    val state: StateFlow<NpwpState> = _state
//
//    fun getOcr(imageData: ByteArray) {
//        viewModelScope.launch {
//            _state.value = NpwpState.Loading
//            try {
//                val result = ocrApi.ocrNpwp(imageData)
//                _state.value = NpwpState.Success(result)
//            } catch (e: Exception) {
//                _state.value = NpwpState.Error(e.message ?: "Terjadi kesalahan")
//            }
//        }
//    }
//
//    fun onClear() {
//        _state.value = NpwpState.Idle
//    }
//}


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NpwpViewModel(
    private val getNPWPUseCase: GetNPWPUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NpwpState>(NpwpState.Idle)
    val state get() = _state.asStateFlow()

    fun getOcr(image: ByteArray) = viewModelScope.launch {
        _state.value = NpwpState.Loading

        val result = getNPWPUseCase.invoke(image)
        result.onSuccess {
            _state.value = NpwpState.Success(it)
        }.onFailure {
            _state.value = NpwpState.Error(it.message.orEmpty())
        }
    }

    fun onClear() {
        _state.value = NpwpState.Idle
    }
}
