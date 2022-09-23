package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by David Madilyan on 31.07.2022.
 */
data class Location(
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    @SerializedName("localtime_epoch") val localtimeEpoch: Long?,
    @SerializedName("tz_id") val tzId: String?,
    val localtime: String?,
)