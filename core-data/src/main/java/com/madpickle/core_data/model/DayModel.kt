package com.madpickle.core_data.model

import com.madpickle.core_network.model.Forecast
import io.realm.RealmList
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
open class DayModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    val idDay: Long,                                                                                //ключ по поиску зависимых объектов в  других таблицах
    val region: String,                                                                             //регион по которому будут кэшироваться прогноз по дням
    val maxTemp: Float?,
    val dateForecast: String?,
    val minTemp: Float?,
    val avgTemp: Float?,
    val uv: Double?,
    val text: String = "",
    val iconUrl: String = "",
    val code: Int,
    val astro: AstronomyModel? = null,
    val hours: RealmList<HourModel>? = null
) : RealmObject() {

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

