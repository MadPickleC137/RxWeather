package com.madpickle.core_android.utils

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