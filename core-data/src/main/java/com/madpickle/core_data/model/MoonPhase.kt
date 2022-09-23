package com.madpickle.core_data.model

/**
 * Created by David Madilyan on 21.09.2022.
 */
enum class MoonPhase(val text: String) {
    Empty(""),
    NewMoon("Новолуние"),
    WaxingCrescent("Молодая луна"),
    FirstQuarter("Первая четверть"),
    WaxingGibbous("Прибывающая луна"),
    FullMoon("Полнолуние"),
    WaningGibbous("Убывающая луна"),
    LastQuarter("Последняя четверть"),
    WaningCrescent("Старая луна"),
}