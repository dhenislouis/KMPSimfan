package org.kmp.simfan.extension

fun toRupiah(d: Double): String {
    val number = d.toLong()
    val formatted = number.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
    return "Rp$formatted"
}

fun Int.toRupiah(): String = this.toDouble().toRupiah()
fun Float.toRupiah(): String = this.toDouble().toRupiah()
