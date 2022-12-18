package com.madpickle.core_android.screens

import androidx.navigation.NavController

/**
 * Created by David Madilyan on 15.12.2022.
 */
sealed class Screen(val title: String){
    class ForecastDetail(_title: String = "Детальная информация по прогнозу погоды"): Screen(_title)
    class CurrentsList(_title: String = "Список добавленых мест для прогнозов"): Screen(_title)
    class SelectRegion(_title: String = "Выбор нового места для списка прогнозов"): Screen(_title)
}
