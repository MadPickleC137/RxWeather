package com.madpickle.core_android

/**
 * Created by David Madilyan on 15.12.2022.
 */

fun String?.getDateString(): String {
    return this?.split("\\s".toRegex())?.toTypedArray()?.first() ?: ""
}

fun String?.getTimeString(): String {
    return this?.split("\\s".toRegex())?.toTypedArray()?.last() ?: ""
}