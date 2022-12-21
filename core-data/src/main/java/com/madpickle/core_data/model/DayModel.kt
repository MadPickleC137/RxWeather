package com.madpickle.core_data.model

import com.madpickle.core_network.model.Forecast
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*
import kotlin.random.Random

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class DayModel(
    var idDayKey: String = UUID.randomUUID().toString(),
    var idDay: Long = 0,                                                                                //ключ по поиску зависимых объектов в  других таблицах
    @Required
    var region: String = "",                                                                             //регион по которому будут кэшироваться прогноз по дням
    var maxTemp: Float? = null,
    var dateForecast: String? = null,
    var minTemp: Float? = null,
    var avgTemp: Float? = null,
    var uv: Double? = null,
    var text: String = "",
    var iconUrl: String = "",
    var code: Int = 0,
    var astro: AstronomyModel? = null,
    var hours: RealmList<HourModel>? = null
) : RealmObject() {

    fun copy(): DayModel{
        return DayModel(idDayKey, idDay, region, maxTemp, dateForecast, minTemp, avgTemp, uv, text, iconUrl, code, astro, hours)
    }

    companion object{
        fun InitDayForecast(dayForecast: Forecast?, region: String): DayModel {
            val idDay = dayForecast?.dateEpoch ?: Random.nextLong(0, Long.MAX_VALUE)
            val hoursRealm = RealmList<HourModel>()
            dayForecast?.hours?.forEach {
                hoursRealm.add(HourModel.InitHourForecastModel(it, idDay))
            }
            return DayModel(
                idDay = idDay,
                region = region,
                dateForecast = dayForecast?.date,
                maxTemp = dayForecast?.day?.maxTemp,
                minTemp = dayForecast?.day?.minTemp,
                avgTemp = dayForecast?.day?.avgTemp,
                uv = dayForecast?.day?.uv,
                text = dayForecast?.day?.condition?.text ?: "",
                iconUrl = dayForecast?.day?.condition?.iconUrl ?: "",
                code = dayForecast?.day?.condition?.code ?: -1,
                astro = AstronomyModel.InitAstronomyModel(dayForecast?.astro, dayForecast?.date, region),
                hours = hoursRealm
            )
        }
    }
}

