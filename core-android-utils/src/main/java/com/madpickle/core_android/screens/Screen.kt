package com.madpickle.core_android.screens

import android.os.Bundle

/**
 * Created by David Madilyan on 15.12.2022.
 */
sealed class Screen(val title: String){
    class ForecastDetail(val region: String, _title: String = "Детальная информация по прогнозу погоды"): Screen(_title){
        companion object {
            const val ARGS = "ARGS_FORECAST"
        }
        fun toBundle() = Bundle().apply {
            putString(ARGS, region)
        }
    }
    class CurrentsList(_title: String = "Список добавленых мест для прогнозов"): Screen(_title)
    class SelectRegion(_title: String = "Выбор нового места для списка прогнозов"): Screen(_title)
}