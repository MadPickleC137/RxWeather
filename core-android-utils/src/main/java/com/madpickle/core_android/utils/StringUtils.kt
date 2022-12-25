package com.madpickle.core_android.utils

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

/**
 * Created by David Madilyan on 15.12.2022.
 */

fun String?.getDateString(): String {
    return this?.split("\\s".toRegex())?.toTypedArray()?.first() ?: ""
}

fun String?.getTimeString(): String {
    return this?.split("\\s".toRegex())?.toTypedArray()?.last() ?: ""
}

fun String?.toUrlIcon(): String{
    return "https:${this}"
}

fun String?.parseAlertDate(start: String): String{
    return (start + " " + this?.replace("T", "  ")?.replaceAfter("+", ""))
}

fun String?.getDayOfWeek(): String {
    val localDate = LocalDate.parse(this)
    return localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}