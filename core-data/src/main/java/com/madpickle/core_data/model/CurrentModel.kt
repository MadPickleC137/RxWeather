package com.madpickle.core_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madpickle.core_network.model.Current

/**
 * Created by David Madilyan on 27.08.2022.
 */
@Entity(tableName = "current_weather")
data class CurrentModel(
    //общие данные о текущей погоде
    @PrimaryKey(autoGenerate = true) val idCurrent: Int = 0,
    val lastUpdated: String?,
    @ColumnInfo(name = "region_current")
    val region: String?,
    val temperature: Double?,
    val isDay: Boolean?,
    val cloud: Int?,
    val humidity: Int?,
    val feelsLike: Double?,
    val windKmp: Double?,
    val uv: Double?,

    //данные об иконке
    val text: String = "",
    val iconUrl: String = "",
    val code: Int,

    //данные о воздухе
    val co: Float? = null,
    val o3: Float? = null,
    val no2: Float? = null,
    val so2: Float? = null,
    val pm25: Float? = null,
    val pm10: Float? = null,
) {

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