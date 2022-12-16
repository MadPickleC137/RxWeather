package com.madpickle.core_android.utils

/**
 * Created by David Madilyan on 25.08.2022.
 */
data class AlertMessageEvent(
    val title: String = "",
    val message: String,
    val action: () -> Unit = {},
    val dismiss: () -> Unit = {},
)