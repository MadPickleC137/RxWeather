package com.madpickle.core_data.model

import com.madpickle.core_network.model.HourForecast
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*
import kotlin.random.Random

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class HourModel(
    var idHour: String = UUID.randomUUID().toString(),
    var idParentDay: Long = 0,                                                                          //ключ по поиску элементов в таблице
    var time: String? = null,
    var temperature: Float? = null,
    var isDay: Boolean? = null,
    var isRain: Boolean? = null,
    var isShow: Boolean? = null,
    var chanceRain: Int? = null,
    var chanceSnow: Int? = null,
    var humidity: Int? = null,                                                                             //влажность
    var cloud: Int? = null,
    var feelsLike: Float? = null,                                                                          //температура по ощущениям
    var windKmp: Double? = 0.0,
    var uv: Double? = null,
    var text: String = "",
    var iconUrl: String = "",
    var code: Int = 0,
): RealmObject() {

    fun getTextUv() = getUvIndex(uv).text

    fun copy(): HourModel{
        return HourModel(idHour, idParentDay, time, temperature, isDay, isRain, isShow, chanceRain, chanceSnow, humidity, cloud, feelsLike, windKmp, uv, text, iconUrl, code)
    }

    companion object{
        fun InitHourForecastModel(forecast: HourForecast, idDay: Long?): HourModel{
            return HourModel(
                idParentDay = idDay ?: Random.nextLong(0, Long.MAX_VALUE),
                time = forecast.time,
                temperature = forecast.temp,
                isDay = forecast.isDay == 1,
                isRain = forecast.willRain == 1,
                isShow = forecast.willSnow == 1,
                chanceRain = forecast.chanceRain,
                chanceSnow = forecast.chanceSnow,
                humidity = forecast.humidity,
                cloud = forecast.cloud,
                feelsLike = forecast.feelsLike,
                windKmp = forecast.windKph,
                uv = forecast.uv,
                text = forecast.condition?.text ?: "",
                iconUrl = forecast.condition?.iconUrl ?: "",
                code = forecast.condition?.code ?: -1,
            )
        }
    }
}