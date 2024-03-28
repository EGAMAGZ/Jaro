package util

import androidx.compose.runtime.Composable

object Util {

    @Composable
    fun roundedPair(number: Float, renderPair: @Composable (Pair<Int, Int>) -> Unit) {
        val integerPart = number.toInt()
        val decimalPart = ((number - number.toInt()) * 100)

        val pair = Pair(integerPart, decimalPart.toInt())
        renderPair(pair)
    }
}