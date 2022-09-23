package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by David Madilyan on 20.08.2022.
 */
data class Astro(
    val sunrise: String?,
    val sunset: String?,
    val moonrise: String?,
    val moonset: String?,
    @SerializedName("moon_phase") val moonPhase: String?,
    @SerializedName("moon_illumination") val moonIlluminate: String?,
)
