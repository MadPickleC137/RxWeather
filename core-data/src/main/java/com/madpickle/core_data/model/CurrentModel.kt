package com.madpickle.core_data.model

import com.madpickle.core_network.model.Current
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 27.08.2022.
 *
 * общие данные о текущей погоде
 */
@RealmClass(embedded = true)
open class CurrentModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    var lastUpdated: String?,
    var region: String?,
    var temperature: Double?,
    var isDay: Boolean?,
    var cloud: Int?,
    var humidity: Int?,
    var feelsLike: Double?,
    var windKmp: Double?,
    var uv: Double?,

    //данные об иконке
    var text: String = "",
    var iconUrl: String = "",
    var code: Int,

    //данные о воздухе
    var co: Float? = null,
    var o3: Float? = null,
    var no2: Float? = null,
    var so2: Float? = null,
    var pm25: Float? = null,
    var pm10: Float? = null,
): RealmObject() {

    companion object{
        fun InitCurrent(currentResponse: Current, region: String?): CurrentModel {
            return CurrentModel(
                lastUpdated = currentResponse.lastUpdated,
                temperature = currentResponse.temperature,
                isDay = currentResponse.isDay == 1,
                region = region,
                text = currentResponse.condition?.text ?: "",
                iconUrl = currentResponse.condition?.iconUrl ?: "",
                code = currentResponse.condition?.code ?: -1,
                cloud = currentResponse.cloud,
                humidity = currentResponse.humidity,
                feelsLike = currentResponse.feelsLike,
                windKmp = currentResponse.windKmp,
                uv = currentResponse.uv,
                co = currentResponse.airQuality?.co,
                o3 = currentResponse.airQuality?.o3,
                no2 = currentResponse.airQuality?.no2,
                so2 = currentResponse.airQuality?.so2,
                pm25 = currentResponse.airQuality?.pm25,
                pm10 = currentResponse.airQuality?.pm10,
            )
        }
    }
}