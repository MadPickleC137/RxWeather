package com.madpickle.core_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madpickle.core_network.model.HourForecast

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Entity(tableName = "hour_forecast")
data class HourForecastModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idParentDay: Long,                                                                          //ключ по поиску элементов в таблице
    val time: String?,
    val minTemp: Float?,
    val isDay: Boolean?,
    val isRain: Boolean?,
    val isShow: Boolean?,
    val chanceRain: Int?,
    val chanceSnow: Int?,
    val humidity: Int?,                                                                             //влажность
    val cloud: Int?,
    val feelsLike: Float?,                                                                          //температура по ощущениям
    val uv: Double?,

    val text: String = "",
    val iconUrl: String = "",
    val code: Int,
){

    companion object{
        fun InitHourForecastModel(forecast: HourForecast, idDay: Long): HourForecastModel{
            return HourForecastModel(
                idParentDay = idDay,
                time = forecast.time,
                minTemp = forecast.minTemp,
                isDay = forecast.isDay == 1,
                isRain = forecast.willRain == 1,
                isShow = forecast.willSnow == 1,
                chanceRain = forecast.chanceRain,
                chanceSnow = forecast.chanceSnow,
                humidity = forecast.humidity,
                cloud = forecast.cloud,
                feelsLike = forecast.feelsLike,
                uv = forecast.uv,
                text = forecast.condition?.text ?: "",
                iconUrl = forecast.condition?.iconUrl ?: "",
                code = forecast.condition?.code ?: -1,
            )
        }
    }
}