package com.madpickle.feature_current_forecast.di

import com.madpickle.core_data.source.CurrentWeatherRepository

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface CurrentsComponentDependencies {
    fun getRepo(): CurrentWeatherRepository
}