package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by David Madilyan on 20.08.2022.
 */
data class ForecastResponse(
    val current: Current?,
    val forecast: ForecastDays?,
    val alerts: Alerts?
): BaseResponse()

data class Alerts(
    val alert: List<Alert>?,
)

data class ForecastDays(
    @SerializedName("forecastday") val forecastDay: List<Forecast>?,
)

data class Forecast(
    val date: String?,
    @SerializedName("date_epoch") val dateEpoch: Long?,
    @SerializedName("day")  val day: DayForecast?,
    val astro: Astro?,
    @SerializedName("hour")
    val hours: List<HourForecast>?,
)

data class HourForecast(
    @SerializedName("time_epoch") val timeEpoch: Long?,
    val time: String?,
    @SerializedName("temp_c") val temp: Float?,
    @SerializedName("is_day") val isDay: Int?,
    @SerializedName("will_it_rain") val willRain: Int?,                                             //1 = Yes 0 = No Будет ли дождь
    @SerializedName("will_it_snow") val willSnow: Int?,                                             //1 = Yes 0 = No Будет ли снег
    @SerializedName("chance_of_rain") val chanceRain: Int?,                                         //Chance of rain as percentage
    @SerializedName("chance_of_snow") val chanceSnow: Int?,                                          //Chance of rain as percentage
    val condition: Condition?,
    val humidity: Int?,                                                                             //влажность
    val cloud: Int?,
    @SerializedName("feelslike_c") val feelsLike: Float?,                                           //температура по ощущениям
    @SerializedName("uv") val uv: Double?,
    @SerializedName("wind_kph") val windKph: Double?,
)

data class DayForecast(
    @SerializedName("avgtemp_c") val avgTemp: Float?,
    val condition: Condition?,
)

