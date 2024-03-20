import kotlinx.datetime.LocalDate
import kotlin.math.truncate

fun LocalDate.daysInYear() = if (isLeapYear()) 366 else 365
fun LocalDate.isLeapYear() = when {
    year % 4 == 0 && year % 100 != 0 -> true
    year % 400 == 0 -> true
    else -> false
}

fun LocalDate.remainingDaysInYear() = daysInYear() - dayOfYear

fun LocalDate.percentageOfYear() = truncate((dayOfYear.toFloat() / daysInYear().toFloat()) * 100).toInt()