package org.kmp.simfan.screen.profile.tandatangan

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset

class SignaturePadState {
    val strokes = mutableStateListOf<MutableList<Offset>>()
    private var currentStroke = mutableStateListOf<Offset>()

    fun startStroke(offset: Offset) {
        currentStroke = mutableStateListOf()
        currentStroke.add(offset)
        strokes.add(currentStroke)
    }

    fun addPoint(offset: Offset) {
        currentStroke.add(offset)
    }

    fun endStroke() {
        currentStroke = mutableStateListOf()
    }

    fun clear() {
        strokes.clear()
    }
}
