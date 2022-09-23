package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by David Madilyan on 20.08.2022.
 */
data class Condition(
    val text: String = "",
    @SerializedName("icon") val iconUrl: String = "",
    val code: Int,
)
