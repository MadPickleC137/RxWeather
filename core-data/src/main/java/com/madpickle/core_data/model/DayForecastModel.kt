package com.madpickle.core_data.model

import androidx.room.*
import com.madpickle.core_network.model.Forecast
import kotlin.random.Random

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Entity(tableName = "day_forecast")
data class DayForecastModel(
    @PrimaryKey(autoGenerate = true) val key: Int = 0,
    val idDay: Long,                                                                                //ключ по поиску зависимых объектов в  других таблицах
    @ColumnInfo(name = "region_day")
    val region: String,                                                                             //регион по которому будут кэшироваться прогноз по дням
    val maxTemp: Float?,
    val dateForecast: String?,
    val minTemp: Float?,
    val avgTemp: Float?,
    val uv: Double?,
    val text: String = "",
    val iconUrl: String = "",
    val code: Int,
    @Embedded
    val astronomy: AstronomyModel? = null,
){
    companion object{
        fun InitDayForecast(dayForecast: Forecast, region: String): DayForecastModel {
            val idDay = dayForecast.dateEpoch ?: Random.nextLong(0, Long.MAX_VALUE)
            return DayForecastModel(
                idDay = idDay,
                region = region,
                dateForecast = dayForecast.date,
                maxTemp = dayForecast.day?.maxTemp,
                minTemp = dayForecast.day?.minTemp,
                avgTemp = dayForecast.day?.avgTemp,
                uv = dayForecast.day?.uv,
                text = dayForecast.day?.condition?.text ?: "",
                iconUrl = dayForecast.day?.condition?.iconUrl ?: "",
                code = dayForecast.day?.condition?.code ?: -1,
                astronomy = dayForecast.astro?.let { AstronomyModel.InitAstronomyModel(it, dayForecast.date) }
            )
        }
    }
}

data class DayForecastWithHours(
    @Embedded
    val dayForecastModel: DayForecastModel,

    @Relation(parentColumn = "idDay", entityColumn = "idParentDay")
    val hours: List<HourForecastModel>? = null
)


