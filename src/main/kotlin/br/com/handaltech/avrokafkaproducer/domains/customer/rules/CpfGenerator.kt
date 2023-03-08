package br.com.handaltech.avrokafkaproducer.domains.customer.rules

import kotlin.math.floor
import kotlin.math.roundToInt

fun generateCpf(): String {
    val n1: Int = getRandom()
    val n2: Int = getRandom()
    val n3: Int = getRandom()
    val n4: Int = getRandom()
    val n5: Int = getRandom()
    val n6: Int = getRandom()
    val n7: Int = getRandom()
    val n8: Int = getRandom()
    val n9: Int = getRandom()

    var d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10

    d1 = 11 - mod(d1)

    if (d1 >= 10) d1 = 0

    var d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11

    d2 = 11 - mod(d2)

    if (d2 >= 10) d2 = 0

    return "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2
}

private fun getRandom(): Int = (Math.random() * 9).toInt()
private fun mod(dividend: Int): Int =
    (dividend - floor((dividend / 11).toDouble()) * 11).roundToInt()
