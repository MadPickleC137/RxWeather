package com.madpickle.core_android

import java.text.DecimalFormat

/**
 * Created by David Madilyan on 25.12.2022.
 */

fun Float?.toCelsiusString(): String{
    val df = DecimalFormat("#.#")
    df.format(this)
    return df.format(this) + " ºC"
}