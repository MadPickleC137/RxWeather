package com.madpickle.core_data.model

import com.madpickle.core_network.model.Astro
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class AstronomyModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    var date: String?,                                                                              //Дата в yyyy-MM-dd формате, по этому свойству осуществляется выборка из бд
    var sunrise: String?,
    var sunset: String?,
    var moonrise: String?,
    var moonset: String?,
    var moonPhase: String?,
    var region: String?,
    var moonIlluminate: String?,
): RealmObject() {
    val textMoonPhase get() = moonPhase?.replace(" ", "")?.let { MoonPhase.valueOf(it) } ?: MoonPhase.Empty

    companion object{
        fun InitAstronomyModel(astro: Astro?, date: String?, region: String): AstronomyModel {
            return AstronomyModel(
                date = date,
                sunrise = astro?.sunrise,
                sunset = astro?.sunset,
                moonrise = astro?.moonrise,
                moonset = astro?.moonset,
                moonPhase = astro?.moonPhase,
                region = region,
                moonIlluminate = astro?.moonIlluminate,
            )
        }
    }
}