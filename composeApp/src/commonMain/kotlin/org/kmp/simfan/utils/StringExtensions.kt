package org.kmp.simfan.utils

fun String.cleanUp(): String {
    if (isEmpty()) return ""

    return this
        .replace("```json", "")
        .replace("```", "")
        .replace("\\n", "")
        .replace("\\", "")
        .trim()
}