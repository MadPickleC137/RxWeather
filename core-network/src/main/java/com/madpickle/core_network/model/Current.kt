package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName
import com.madpickle.core_network.model.AirQuality
import com.madpickle.core_network.model.Condition

/**
 * Created by David Madilyan on 31.07.2022.
 */
data class Current(
    @SerializedName("last_updated") val lastUpdated: String?,
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long?,
    @SerializedName("temp_c") val temperature: Double?,
    @SerializedName("humidity") val humidity: Int?,
    @SerializedName("is_day") val isDay: Int?,                                       //	1 = Да 0 = Нет Отображать значок дневного или ночного режима
    val condition: Condition?,
    val cloud: Int?,                                                                 // облачность в процентах
    @SerializedName("feelslike_c") val feelsLike: Double?,
    @SerializedName("wind_kph") val windKmp: Double?,                                //скорость ветра в км/час
    @SerializedName("uv") val uv: Double?,                                           //UV Index  https://ru.wikipedia.org/wiki/Ультрафиолетовый_индекс
    @SerializedName("air_quality") val airQuality: AirQuality?,
)