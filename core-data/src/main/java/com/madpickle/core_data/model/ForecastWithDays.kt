package com.madpickle.core_data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.madpickle.core_network.model.ForecastResponse

/**
 * Created by David Madilyan on 31.08.2022.
 */
data class ForecastWithDays(
    @Embedded
    val current: CurrentModel? = null,
    @Embedded
    val location: LocationModel? = null,
    @Relation(parentColumn = "region", entityColumn = "region_day")
    val days: List<DayForecastModel>? = null,

    @Relation(parentColumn = "region", entityColumn = "region_alert")
    val alerts: List<AlertModel>? = null,
){
    companion object{
        fun InitForecastWithDays(response: ForecastResponse): ForecastWithDays {
            val regionRes = response.location?.region ?: ""
            return ForecastWithDays(
                current = response.current?.let {
                    CurrentModel.InitCurrent(it, response.location?.region)
                },
                location = LocationModel.InitLocation(response),
                days = response.forecast?.forecastDay?.map {
                    DayForecastModel.InitDayForecast(it, regionRes)
                },
                alerts = response.alerts?.alert?.map {
                    AlertModel.InitAlertModel(it, regionRes)
                }
            )
        }
    }
}