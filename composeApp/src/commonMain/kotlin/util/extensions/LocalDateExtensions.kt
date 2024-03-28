package util.extensions

import kotlinx.datetime.LocalDate
import kotlin.math.truncate

inline val LocalDate.daysInYear: Int get() = if (isLeapYear) 366 else 365
inline val LocalDate.isLeapYear: Boolean
    get() = when {
        year % 4 == 0 && year % 100 != 0 -> true
        year % 400 == 0 -> true
        else -> false
    }

inline val LocalDate.remainingDaysInYear: Int get() = daysInYear - dayOfYear

inline val LocalDate.percentageOfYear: Float
    get() {
        val percentage = (dayOfYear.toFloat() / daysInYear.toFloat()) * 100
        val rounded = (percentage * 100).toInt() / 100f

        return rounded
    }

inline val LocalDate.roundedPercentageOfYear: Float get() = truncate(percentageOfYear)