package com.madpickle.core_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madpickle.core_network.model.Astro

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Entity(tableName = "astronomy")
data class AstronomyModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String?,                                                                              //Дата в yyyy-MM-dd формате, по этому свойству осуществляется выборка из бд
    val sunrise: String?,
    val sunset: String?,
    val moonrise: String?,
    val moonset: String?,
    val moonPhase: String?,
    val moonIlluminate: String?,
){
    val textMoonPhase get() = moonPhase?.replace(" ", "")?.let { MoonPhase.valueOf(it) } ?: MoonPhase.Empty

    companion object{
        fun InitAstronomyModel(astro: Astro, date: String?): AstronomyModel {
            return AstronomyModel(
                date = date,
                sunrise = astro.sunrise,
                sunset = astro.sunset,
                moonrise = astro.moonrise,
                moonset = astro.moonset,
                moonPhase = astro.moonPhase,
                moonIlluminate = astro.moonIlluminate,
            )
        }
    }
}
