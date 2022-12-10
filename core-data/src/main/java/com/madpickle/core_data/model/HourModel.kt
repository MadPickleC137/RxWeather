package com.madpickle.core_data.model

import com.madpickle.core_network.model.HourForecast
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*
import kotlin.random.Random

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class HourModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    var idParentDay: Long = 0,                                                                          //ключ по поиску элементов в таблице
    var time: String?,
    var minTemp: Float?,
    var isDay: Boolean?,
    var isRain: Boolean?,
    var isShow: Boolean?,
    var chanceRain: Int?,
    var chanceSnow: Int?,
    var humidity: Int?,                                                                             //влажность
    var cloud: Int?,
    var feelsLike: Float?,                                                                          //температура по ощущениям
    var uv: Double?,
    var text: String = "",
    var iconUrl: String = "",
    var code: Int,
): RealmObject() {

    companion object{
        fun InitHourForecastModel(forecast: HourForecast, idDay: Long?): HourModel{
            return HourModel(
                idParentDay = idDay ?: Random.nextLong(0, Long.MAX_VALUE),
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