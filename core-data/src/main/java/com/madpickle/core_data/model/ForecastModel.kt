package com.madpickle.core_data.model

import com.madpickle.core_network.model.ForecastResponse
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 31.08.2022.
 *
 * Класс сущность содержит в себе прогноз погоды для нескольких дней, каждый день расписан по часам
 */
open class ForecastModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    val region: String? = null,
    val current: CurrentModel? = null,
    val location: LocationModel? = null,
    val daysForecast: RealmList<DayModel>? = null,
    val alerts: RealmList<AlertModel>? = null
): RealmObject() {
    companion object{
        fun InitForecastWithDays(response: ForecastResponse): ForecastModel {
            val regionRes = response.location?.region ?: ""
            val realmAlerts = RealmList<AlertModel>()
            val realmDays= RealmList<DayModel>()
            response.alerts?.alert?.forEach {
                realmAlerts.add(AlertModel.InitAlertModel(it, regionRes))
            }
            response.forecast?.forecastDay?.forEach { forecast ->
                realmDays.add(DayModel.InitDayForecast(forecast, regionRes))
            }
            return ForecastModel(
                region = regionRes,
                current = response.current?.let {
                    CurrentModel.InitCurrent(it, response.location?.region)
                },
                location = response.location?.let{
                     LocationModel.InitLocation(it)
                },
                daysForecast = realmDays,
                alerts = realmAlerts
            )
        }
    }
}