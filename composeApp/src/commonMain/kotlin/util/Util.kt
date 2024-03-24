package util

object Util {

    fun roundedPair(number: Float, pair: (Pair<Int, Int>) -> Unit) {

        val (first, second) = Pair(number.toInt(), ((number - number.toInt()) * 100).toInt())
        pair(Pair(first, second))
    }
}