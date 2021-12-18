package com.goodcode.alucard.utils

fun <K, V> Iterable<Pair<K, V>>.toMap(): Map<K, V> {
    val result = HashMap<K, V>()
    for (x in this) {
        val (k, v) = x
        result.put(k, v)
    }
    return result
}
