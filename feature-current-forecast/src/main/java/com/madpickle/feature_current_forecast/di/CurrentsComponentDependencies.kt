package com.madpickle.feature_current_forecast.di

import com.madpickle.core_data.repositories.ICurrentWeatherRepository
import com.madpickle.core_data.repositories.ILocationRepository

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface CurrentsComponentDependencies {
    fun getCurrentRepo(): ICurrentWeatherRepository
    fun getLocationRepo(): ILocationRepository
}