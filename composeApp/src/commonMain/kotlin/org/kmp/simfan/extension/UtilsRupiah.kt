package org.kmp.simfan.extension

fun Double.toRupiah(): String {
    val number = this.toLong()
    val formatted = number.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return "Rp$formatted"
}

fun Int.toRupiah(): String = this.toDouble().toRupiah()
fun Float.toRupiah(): String = this.toDouble().toRupiah()
