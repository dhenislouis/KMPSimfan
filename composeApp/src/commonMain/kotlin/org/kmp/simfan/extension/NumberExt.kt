package org.kmp.simfan.extension

fun Long.toRupiah(): String {
    val formatted = this.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return formatted
}

fun Double.toRupiah(): String {
    val number = this.toLong()
    val formatted = number.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return formatted
}